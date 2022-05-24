/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;


import com.hotel.managment.View.UsersView.Booking;
import com.hotel.managment.View.UsersView.Home;
import com.hotel.managment.View.UsersView.LoginScreen;
import com.hotel.managment.View.UsersView.UserPanel;
import com.hotel.managment.model.Room;
import com.hotel.managment.model.RoomCategory;
import com.hotel.managment.model.RoomType;
import com.hotel.managment.model.Users;
import com.hotel.managment.utils.DoublyLinkList;
import com.hotel.managment.utils.LocalStorage;
import com.hotel.managment.utils.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class HomeController {
    
    
    
    
    
    
    private  Home View = null;
    private SQL con = null;
    public  DoublyLinkList<Room> room =  new DoublyLinkList();
    public  DoublyLinkList<RoomCategory> RoomCategory =  new DoublyLinkList();
    public  DoublyLinkList<RoomType> RoomType =  new DoublyLinkList();
    public Room selected = null;
    private  Users currentUser = null; 
    private  LocalStorage store;
    
    
    
    public HomeController(Home View) throws ClassNotFoundException, SQLException{
       this.View = View;
       this.con = new SQL("hotelmanagement");
       this.store = new LocalStorage();
        if(this.store.get("uid") != null){
        this.View.auth.setText("Profile");
        }
       this.init();
    }
    
    
    
    public void init() throws ClassNotFoundException{
  
        this.fetchRoom();
        
        
        this.loadroom();
        
        this.View.proceed.addActionListener((e)->{
            try {
                proceedClicked();
            } catch (ClassNotFoundException ex) {
                print("Error : "+ex);
            } catch (SQLException ex) {
               print("Error : "+ex);
            }
        });
        this.View.auth.addActionListener(e -> {
            try {
                authBtnClicked();
            } catch (ClassNotFoundException ex) {
                print("Error : "+ex);
            } catch (SQLException ex) {
                print("Error : "+ex);
            }
        } );
       
        
    
    }
    
    
    public void authBtnClicked() throws ClassNotFoundException, SQLException{
    
        
        print(this.store.get("uid"));
        
        if(this.store.get("uid") != null){
            new UserPanel().setVisible(true);
            
        }else{
        
        new LoginScreen().setVisible(true);
        }
        
        
    
    }
    
    
    public void proceedClicked() throws ClassNotFoundException, SQLException{
     
        if(this.selected != null){
        new Booking(this.selected.getRoom_id()).setVisible(true);
        }else{
        show("Please Select a room! Before Proceeding..");
        }
        
    }
    public void TableClicked(){
     DefaultTableModel model = (DefaultTableModel) this.View.jTable1.getModel();
        int index = this.View.jTable1.getSelectedRow();
        String rooom_id = model.getValueAt(index, 3).toString();

        this.room.find((res)->{
           Room temp = (Room) res;
           
           if(temp.getRoom_id().matches(rooom_id)){
           
               
               this.selected = temp;
               
               this.View.room_title.setText("Room Title : "+this.selected.getroom_cat_title());
               this.View.room_charges.setText("Room charges : "+this.selected.getRoom_charges()+"$/night");
               this.View.room_type_text.setText("Room Type : "+this.selected.getroom_type_title());
           }
            
        });
        
        
        
        
    }
    
    
        public void fetchRoom() throws ClassNotFoundException{
        
            try {
                ResultSet rs=  this.con.select("SELECT * FROM room, room_category, room_type\n" +
                    "  WHERE room.room_cat_id = room_category.room_cat_id\n" +
                    "  AND room.room_type_id = room_type.room_type_id");
                room.clear();
                while(rs.next()){
                    Room r = new Room();
                    r.setRoom_id(rs.getString("room_id"));
                    r.setRoom_cat_id(rs.getString("room_cat_id"));
                    r.setRoom_type_id(rs.getString("room_type_id"));
                    r.setRoom_charges(rs.getString("room_charges"));
                    r.setStatus(rs.getString("status"));
                    r.setroom_type_title(rs.getString("room_type_name"));
                    r.setroom_cat_title(rs.getString("room_cat_name"));
                    
                    room.insertAtFirst(r);
                }
            } catch (SQLException ex) {
                print("Error : "+ex);
            }
          
        }

     public void loadroom(){
                   DefaultTableModel model1 = (DefaultTableModel) this.View.jTable1.getModel();
              model1.setRowCount(0);
              
   
        
              this.room.find((res)->{
                  Room c = (Room) res;        
                 Object[] row = { c.getroom_cat_title()  , c.getRoom_charges() ,  c.getroom_type_title() ,c.getRoom_id() };
                 DefaultTableModel model = (DefaultTableModel) this.View.jTable1.getModel();
                 model.addRow(row);
              
              });

     
     }
            
      public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }
    
}
