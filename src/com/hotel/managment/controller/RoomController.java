/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.View.AdminView.menu2;
import com.hotel.managment.model.Room;
import com.hotel.managment.model.RoomCategory;
import com.hotel.managment.model.RoomType;
import com.hotel.managment.utils.DoublyLinkList;
import com.hotel.managment.utils.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class RoomController {
 
    private  menu2 View = null;
    private SQL con = null;
    public  DoublyLinkList<Room> room =  new DoublyLinkList();
    public  DoublyLinkList<RoomCategory> RoomCategory =  new DoublyLinkList();
    public  DoublyLinkList<RoomType> RoomType =  new DoublyLinkList();
    
    public RoomController(menu2 View) throws ClassNotFoundException, SQLException{
       this.View = View;
       this.con = new SQL("hotelmanagement");
       this.init();
    }
    
    private void init() throws ClassNotFoundException, SQLException{
          
        // Load Data from database
          this.fetchCategory();
          this.fetchRoomType();
          this.fetchRoom();
          
        
        // Rendering UI
          this.loadCategory();
          this.loadRoomType();
          this.loadroom();
          
          // registering Events
       this.View.add.addActionListener( e -> {
              try {
                  this.AddRoom();
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(RoomController.class.getName()).log(Level.SEVERE, null, ex);
              }
          } );
        
        this.View.remove_btn.addActionListener( e-> {
              try {
                  RemoveRoomButtonClicked();
              } catch (ClassNotFoundException ex) {
                 show("Class Not Found Error : "+ex);
              }
          });
        
       
    }
  
    
    
    public void RemoveRoomButtonClicked() throws ClassNotFoundException {
    
        try {
            String roomID = this.View.jTextField1.getText();
            boolean res =  this.con.delete("DELETE FROM `room` WHERE room_id = "+roomID+" and status = 'available'");
            show(res ? "Room Removed From Databsee" : "Failed To Remov room\nPossible Reason : Already booked or Wrong Room Id");
            if(res){
            this.fetchRoom();
            this.loadroom();
            }
        } catch (SQLException ex) {
            show(ex);
        }
    
    }
    
    
    private void AddRoom() throws ClassNotFoundException{
       
     
     String room_charges = (String) this.View.room_charges.getSelectedItem();
     String room_cat_id = null;
     String room_type_id = null;
     
     for(int x = 0 ; x < this.RoomCategory.Size() ; x++){
         RoomCategory temp = (RoomCategory) this.RoomCategory.getAt(x);
         
         if(temp.getRoom_cat_name().matches((String) this.View.room_category.getSelectedItem())){
           room_cat_id = temp.getRoom_cat_id();
             break;
         }
         
     }
     for(int x = 0 ; x < this.RoomType.Size() ; x++){
         RoomType temp = (RoomType) this.RoomType.getAt(x);
         
         if(temp.getRoom_type_name().matches((String) this.View.room_type.getSelectedItem())){
           room_type_id = temp.getroom_type_id();
             break;
         }
         
     }
     
        Room r = new Room();
        r.setRoom_type_id(room_type_id);
        r.setRoom_charges(room_charges);
        r.setRoom_cat_id(room_cat_id);
        show( AddRoom(r)  ? "New Record Added" :"Failed To Add record");
        this.fetchRoom();
        this.loadroom();
      
        
    }

     public boolean AddRoom(Room obj){
        
         String Query = "INSERT INTO `room`( `room_cat_id`, `room_type_id`, `room_charges`, `status`) VALUES ";
         String Param = "('"+obj.getRoom_cat_id()+"','"+obj.getRoom_type_id()+"','"+obj.getRoom_charges()+"','available')";
         Boolean response =  this.con.insert(Query+Param);
         return response;   
        }
        
        
        
        // fetch all room data
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
                 Object[] row = { c.getroom_cat_title()  , c.getRoom_charges() ,  c.getroom_type_title() , c.getStatus() ,c.getRoom_id() };
                 DefaultTableModel model = (DefaultTableModel) this.View.jTable1.getModel();
                 model.addRow(row);
              
              });

     
     }

      public void fetchCategory() throws SQLException{
       ResultSet rs =  this.con.select("SELECT * FROM `room_category`");
          while(rs.next()){
            RoomCategory c = new RoomCategory();
            c.setRoom_cat_id(rs.getString("room_cat_id"));
            c.setRoom_cat_name(rs.getString("room_cat_name"));
            RoomCategory.insertAtFirst(c);
          }
      }
     
  
      public void loadCategory(){
          String b[] = new String[this.RoomCategory.Size()];

          
          for(int i = 0 ; i < this.RoomCategory.Size() ; i++){
          
               RoomCategory s = (RoomCategory) this.RoomCategory.getAt(i);
               b[i] = s.getRoom_cat_name();        
          }
          this.View.room_category.setModel(new javax.swing.DefaultComboBoxModel(b));
         }

             
      public void fetchRoomType() throws SQLException{
       ResultSet rs =  this.con.select("SELECT * FROM `room_type`");
          while(rs.next()){
            RoomType c = new RoomType();
            c.setroom_type_id(rs.getString("room_type_id"));
            c.setRoom_type_name(rs.getString("room_type_name"));
            RoomType.insertAtFirst(c);
          }
      }
     
  
      public void loadRoomType(){
          String b[] = new String[this.RoomType.Size()];

          
          for(int i = 0 ; i < this.RoomType.Size() ; i++){
          
               RoomType s = (RoomType) this.RoomType.getAt(i);
               b[i] = s.getRoom_type_name();        
          }
          this.View.room_type.setModel(new javax.swing.DefaultComboBoxModel(b));
         }

        
      public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }

  
      
                   
}
