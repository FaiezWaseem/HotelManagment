/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.View.UsersView.menu2;
import com.hotel.managment.model.BookingHistory;
import com.hotel.managment.model.Room;
import com.hotel.managment.utils.DoublyLinkList;
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
public class BookiingHistoryController {
 
    private UserController user = null;
    private SQL con = null;
    private menu2 View =null;
   public  DoublyLinkList<BookingHistory> BookingList =  new DoublyLinkList();
    
    
    
    public BookiingHistoryController(menu2 View) {
        try {
            this.user = new UserController();
            this.con = new SQL("hotelmanagement");
            this.View = View;
            this.init();
        } catch (ClassNotFoundException ex) {
           print(ex);
        } catch (SQLException ex) {
            show(ex);
        }
    }
    
    private void init(){
      
        
     loadHistory();
     renderTable();
     
        
    }
    
    public void loadHistory() {
        try {
            this.BookingList.clear();
            print(this.user.getUserId());
            ResultSet rs =  this.con.select("SELECT * FROM `booking_history` where customer_id =" + this.user.getUserId());
            while(rs.next()){
                BookingHistory b = new BookingHistory();
                b.setCheck_in(rs.getString("check_in"));
                b.setCheck_out(rs.getString("check_out"));
                b.setCustomer_id(rs.getString("customer_id"));
                b.setHistory_id(rs.getString("history_id"));
                b.setRoom_title(rs.getString("room_title"));
                b.setRoom_charges(rs.getString("room_charges"));
                this.BookingList.insertAtFirst(b);
            }
        } catch (SQLException ex) {
            show(ex);
        }
        
    }
 
    
    public void renderTable(){
       DefaultTableModel model1 = (DefaultTableModel) this.View.jTable1.getModel();
              model1.setRowCount(0);
              
   
             if(this.BookingList.Size() != 0){
              this.BookingList.find((res)->{
                  BookingHistory c = (BookingHistory) res;        
                 Object[] row = {c.getHistory_id()  , c.getRoom_title() , c.getRoom_charges() , c.getCheck_in() , c.getCheck_out()};
                 DefaultTableModel model = (DefaultTableModel) this.View.jTable1.getModel();
                 model.addRow(row);
              
              });
             }
        
    }

  public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }
}
