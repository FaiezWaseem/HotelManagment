
package com.hotel.managment.lib;

import java.sql.*;

public class SQL {
   
  private Connection con= null;
  private String url = "jdbc:mysql://localhost:3306/";
    
    public SQL(String Tablename) throws ClassNotFoundException, SQLException{
    
          Class.forName("com.mysql.jdbc.Driver");
          this.con = DriverManager.getConnection(this.url +Tablename,"root",""); 
    
   }
    
    public ResultSet select(String query) throws SQLException{
     Statement  s = this.con.createStatement();
     ResultSet rs= s.executeQuery(query);  
    return rs;
    }
    
    public boolean insert(String query) throws SQLException{
    PreparedStatement statement = this.con.prepareStatement(query);
     int rowsInserted = statement.executeUpdate();
      return (rowsInserted > 0);
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
    
}



//  SQL command = new SQL("test");
//               
//               // select Query Example 
//              ResultSet rs= command.select("select * from emp");
//              while(rs.next()){
//                System.out.println(rs.getString("id") + "  " + rs.getString("name") );
//              }