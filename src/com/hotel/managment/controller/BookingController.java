/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.View.AdminView.menu4;
import com.hotel.managment.View.UsersView.menu1;
import com.hotel.managment.model.Booking;
import com.hotel.managment.model.Room;
import com.hotel.managment.utils.DoublyLinkList;
import com.hotel.managment.utils.LocalStorage;
import com.hotel.managment.utils.MyDate;
import com.hotel.managment.utils.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class BookingController {
    
    private String GetRoomquery= "SELECT * FROM room, room_category, room_type \n" +
"WHERE room.room_cat_id = room_category.room_cat_id  \n" +
"AND room.room_type_id = room_type.room_type_id \n" +
"and room.room_id = ";
    
    private String GetMybookingQuery = "SELECT * FROM booking, room_category, room_type , room\n" +
"WHERE booking.room_id = room.room_id  \n" +
"AND room.room_cat_id = room_category.room_cat_id \n" +
"AND room.room_type_id = room_type.room_type_id \n" +
"and booking.customer_id = ";
    
    
   private SQL con;
   public  DoublyLinkList<Booking> Bk =  new DoublyLinkList();
   private JTable table;
   private menu1 view;
   private menu4 AdminBooking = null;
   private boolean  isBooking = false;
   private Booking Currentbooking = null;
   
    
    public BookingController() throws ClassNotFoundException, SQLException{
      this.con = new SQL("hotelmanagement");
    }
    public BookingController(menu4 AdminBooking) throws ClassNotFoundException, SQLException{
      this.con = new SQL("hotelmanagement");
      this.AdminBooking = AdminBooking;
      this.getRoom();
      this.loadTable();
      this.AdminBooking.cancel_booking.addActionListener(e -> {
          try {
              ButtonCancelBookingClicked();
          } catch (SQLException ex) {
                  show(ex);
          }
      });
    }
    
    public BookingController(menu1 view) throws ClassNotFoundException, SQLException{
    this.view = view;
    this.bookingTextDetails();
    this.view.rSMaterialButtonRectangle1.setEnabled(isBooking);
    this.view.rSMaterialButtonRectangle1.addActionListener(e -> Btn());
    this.con = new SQL("hotelmanagement");
    this.Currentbooking = new Booking();
    this.getBookingStatus();
    
    }
    
    
    
    
    public void ButtonCancelBookingClicked() throws SQLException{
   
        String bk_id = this.AdminBooking.bk_id.getText();
        if(bk_id.length() > 0){
            this.Bk.find(e ->{
              Booking b = (Booking) e;
              if(b.getbooking_id().matches(bk_id)){
                  try {
                      boolean res1 =   this.con.delete("DELETE FROM `booking` WHERE bk_id  = "+bk_id);   
                      this.con.update("UPDATE `room` SET `status`='available' WHERE room_id ="+b.getRoom_id());
                       show(res1 ? "Room booking Canceled": "Failed To Cancle booking");
                       if(res1){
                           this.getRoom();
                            this.loadTable();
                       }
                  } catch (SQLException ex) {
                      show(ex);
                  }
              }
              
            });
          
        }
        
    }
    
    
    
      private void Btn(){
        if(this.isBooking){
            try {
                float totalbill = CheckOutTotalBill();
               boolean result =   Checkout(totalbill);
               this.view.booking_info.setVisible(result);
               this.isBooking = !result;
               bookingTextDetails();
               show(result ? "Booking Completed": "Failed to Complete ");
            } catch (SQLException ex) {
              show(ex);
            } catch (ClassNotFoundException ex) {
                print(ex);
            }
            
            
        }else{
        show("No booking Found");
        this.view.rSMaterialButtonRectangle1.setEnabled(isBooking);
        }
      
      }
      
      private float CheckOutTotalBill(){
      MyDate date = new MyDate();
            int NoOfDaysStayed = date.DifferenceOfDays(this.Currentbooking.getCheck_in_date() , this.Currentbooking.getCheck_out_date());
            int charges =  Integer.parseInt(this.Currentbooking.getRoom_Charges());
            
            float Total = NoOfDaysStayed * charges;
            return Total;
      }
      
      private boolean Checkout(float total) throws SQLException, ClassNotFoundException{
       boolean res1 =   this.con.delete("DELETE FROM `booking` WHERE bk_id  = "+this.Currentbooking.getbooking_id());
          String param = "VALUES ('"+this.Currentbooking.getRoom_Title()+"','"+total+"','"+this.Currentbooking.getCheck_in_date()+"','"+this.Currentbooking.getCheck_out_date()+"','"+new UserController().getUserId()+"')";
        boolean res2 =  this.con.insert("INSERT INTO `booking_history`( `room_title`, `room_charges`, `check_in`, `check_out`, `customer_id`)"+param);
        String room_id = this.Currentbooking.getRoom_id();
        this.con.update("UPDATE `room` SET `status`='available' WHERE room_id ="+room_id);
        return (res1 && res2);
      }
      
      

      private void getBookingStatus() throws SQLException{
          LocalStorage store = new LocalStorage();
      
        ResultSet rs = this.con.select(GetMybookingQuery+store.get("uid"));
        
        if(rs.next()){
          String Room = rs.getString("room_cat_name");
          String  Checkin = rs.getString("check_in_date");
          String  CheckOut = rs.getString("check_out_date");
          String Charges = rs.getString("room_charges");
          this.Currentbooking.setRoom_id(rs.getString("room_id"));
          this.Currentbooking.setbooking_id(rs.getString("bk_id"));
          this.Currentbooking.setCheck_in_date(Checkin);
          this.Currentbooking.setCheck_out_date(CheckOut);
          this.Currentbooking.setRoomCharges(Charges);
          this.Currentbooking.setRoom_Title(Room);
          this.view.room.setText("Room : "+Room);
          this.view.charges.setText("Charges : "+Charges+"$/night");
          this.view.checkin.setText("Check-in Date : "+Checkin);
          this.view.checkout.setText("Check Out Date (Booked Till) : "+CheckOut);
          this.view.booking_info.setVisible(false);
          this.isBooking = !isBooking;
            this.bookingTextDetails();
            this.view.rSMaterialButtonRectangle1.setEnabled(isBooking);
        }
        
        
      } 
      private void bookingTextDetails(){
        this.view.charges.setVisible(isBooking);
        this.view.checkin.setVisible(isBooking);
        this.view.checkout.setVisible(isBooking);
        this.view.room.setVisible(isBooking);
      }
    
    
    
      public  Room getRoom(String Id) throws SQLException{
        Room room = new Room();
            
        ResultSet rs = this.con.select(GetRoomquery+Id);
        while(rs.next()){
            room.setRoom_id(Id);
            room.setRoom_charges(rs.getString("room_charges"));
            room.setroom_cat_title(rs.getString("room_cat_name"));
            room.setStatus(rs.getString("status"));
            room.setRoom_cat_id(rs.getString("room_cat_id"));
            room.setRoom_type_id(rs.getString("room_type_id"));
        }
          
        
        return room;
      }
    
    
      public <T> void bookRoom(T obj , T date) throws SQLException{
         
          MyDate d =new MyDate();
          
         String selected = d.convertDateToString((Date) date);
 
         Room r = (Room) obj;
         LocalStorage store = new LocalStorage();
          
         if(!(r.getStatus().matches("booked"))){
          Date now = new Date();
          if(now.before((Date) date)){
             
              String param = "('"+r.getRoom_id()+"','"+store.get("uid")+"','"+d.convertDateToString(now)+"','"+selected+"')";
             boolean res= this.con.insert("INSERT INTO `booking`( `room_id`, `customer_id`, `check_in_date`, `check_out_date`) VALUES "+param);
              
             if(res){
             this.con.update("UPDATE `room` SET `status`='booked' WHERE room_id = "+ r.getRoom_id());
             }
              show(res ? "Room Booked Successfully" : "Failed To Book" );
              
          }else{
            show("Please Select  Correct Date");
          
          }
         }else{
           show("Room Already booked \n Please Choose Another Room");
         
         }
          
      }
      
      
      
      private void getRoom() throws SQLException{
           
          this.Bk.clear();
        ResultSet rs = this.con.select("SELECT * FROM vw_booking;");
          while(rs.next()){
              Booking b = new Booking();
              b.setCheck_in_date(rs.getString("check_in_date"));
              b.setCheck_out_date(rs.getString("check_out_date"));
              b.setCustomer_id(rs.getString("customer_id"));
              b.setRoom_id(rs.getString("room_id"));
              b.setbooking_id(rs.getString("bk_id"));
              b.setRoom_Title(rs.getString("room_cat_name"));
              
             this.Bk.insertAtFirst(b);
          }
          
       

      }
      
      private void loadTable(){
          DefaultTableModel model1 = (DefaultTableModel) this.AdminBooking.jTable1.getModel();
              model1.setRowCount(0);
              
   
        if(this.Bk.Size() != 0)
              this.Bk.find((res)->{
                  Booking c = (Booking) res;        
                 Object[] row = { c.getRoom_Title(), c.getbooking_id() , c.getCustomer_id() , c.getCheck_in_date() , c.getCheck_out_date() };
                 DefaultTableModel model = (DefaultTableModel) this.AdminBooking.jTable1.getModel();
                 model.addRow(row);
              
              });
      
      
      }
      
      
      public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }

      public String InttoString(int i ){
       return Integer.toString(i);
     }
    
}
