/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.View.AdminView.menu1;
import com.hotel.managment.View.UsersView.Home;
import com.hotel.managment.utils.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class AdminHomeController {
    
    private String Query = "SELECT * FROM `vw_admin_home`";
    private  menu1 View = null;
    private SQL con = null;
    
    
    public AdminHomeController(menu1 View) throws ClassNotFoundException, SQLException{
    this.View = View;
    this.con = new SQL("hotelmanagement");  
    this.init();
    }
    
    public void init() throws SQLException{
     ResultSet rs =  this.con.select(Query);
      while(rs.next()){
          this.View.user_txt.setText(rs.getString("users"));
          this.View.room_txt.setText(rs.getString("rooms"));
          this.View.emp_txt.setText(rs.getString("employees"));
          this.View.bk_txt.setText(rs.getString("bookings"));
      }
    
    }
   
    
}
