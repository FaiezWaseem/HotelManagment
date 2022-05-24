/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.model.Users;
import com.hotel.managment.utils.Base64Encryption;
import com.hotel.managment.utils.LocalStorage;
import com.hotel.managment.utils.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class UserController {
 
   private SQL con = null;
   private LocalStorage store = null;
   private Users user =null;
   
   public UserController() throws ClassNotFoundException, SQLException{
      this.con = new SQL("hotelmanagement");
      this.store = new LocalStorage();
      this.user = new Users();
      if(this.store.get("uid") != null){
           this.user.setCustomer_name(this.store.get("username"));
           this.user.setcustomer_id(this.store.get("uid"));
      }
      
   }
   
   
    
    
    
    public boolean register(Users user) throws SQLException{
    
         
        Base64Encryption p = new Base64Encryption();
      
         boolean result =  this.con.insert("INSERT INTO `users`(`customer_name`, `email`, `password`) VALUES ('"+user.getCustomer_name()+"','"+user.getEmail()+"','"+p.Encrypt(user.getpassword())+"')");
       
         return result;
        
    }
    
    
     public boolean Login(Users user) throws SQLException{
         Base64Encryption p = new Base64Encryption();
         String Password = p.Encrypt(user.getpassword());
       boolean res = false;
       System.out.println(user.toString());  
       System.out.println("select * from users where email ='"+user.getEmail()+"' and password = '"+Password+"'");
       ResultSet rs =  this.con.select("select * from users where email ='"+user.getEmail()+"' and password = '"+Password+"'");
         while(rs.next()){
              this.store.set("uid", rs.getString("customer_id"));
              this.store.set("username", rs.getString("customer_name")); 
              this.store.set("email", rs.getString("email")); 
              res = true;
         }
               show(res ? "Logged In" : "Failed To Log-In \n:Error File UserController");
         return res;
         
     }
    
     public void LogOut(){
       this.store.clearAll();
       show("Logged Out");
      
     }
     
     public String getUserName(){
      return this.user.getCustomer_name();
     }
     public String getUserId(){
      return this.user.getcustomer_id();
     }
    
     
     public boolean UpdateUsername(String newname) throws SQLException{
     
         boolean res = this.con.update("UPDATE `users` SET `customer_name`='"+newname+"' WHERE customer_id = "+this.getUserId());
         
         if(res){
         this.store.set("username", newname);
         }
        return res;
         
     }
     
        public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }
}
