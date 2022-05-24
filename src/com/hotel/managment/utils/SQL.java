
package com.hotel.managment.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SQL {
   
  private Connection con= null;
  private String url = "jdbc:mysql://localhost:3306/";
    
    public SQL(String DatabaseName) throws ClassNotFoundException, SQLException{
    
          Class.forName("com.mysql.jdbc.Driver");
          this.con = DriverManager.getConnection(this.url +DatabaseName,"root",""); 
    
   }
    
    public ResultSet select(String query) throws SQLException{
     Statement  s = this.con.createStatement();
     ResultSet rs= s.executeQuery(query);  
    return rs;
    }
    
    public boolean insert(String query) {
      try {
          PreparedStatement statement = this.con.prepareStatement(query);
          int rowsInserted = statement.executeUpdate();
          return (rowsInserted > 0);
      } catch (SQLException ex) {
          if(ex.toString().contains("customer_id")){
          show("You Already Have A Booking");
          }else{
          show("SQL Error: " +ex);
          }
          return false;
      }
 
    }
    
    
    public boolean delete(String query) throws SQLException{
        PreparedStatement statement = this.con.prepareStatement(query);
         int rowsInserted = statement.executeUpdate();
         return (rowsInserted > 0);
    }
    
      public boolean update(String query) throws SQLException{
        PreparedStatement statement = this.con.prepareStatement(query);
         int rowsInserted = statement.executeUpdate();
         return (rowsInserted > 0);
    }
    
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }
}



//  SQL command = new SQL("test");
//               
//               // select Query Example 
//              ResultSet rs= command.select("select * from emp");
//              while(rs.next()){
//                System.out.println(rs.getString("id") + "  " + rs.getString("name") );
//              }