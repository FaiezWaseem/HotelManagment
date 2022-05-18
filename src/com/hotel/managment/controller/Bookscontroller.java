/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.DataStructure.DoublyLinkList;
import com.hotel.managment.lib.*;
import com.hotel.managment.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class Bookscontroller {
    
      public  DoublyLinkList<Book> BooksTable =  new DoublyLinkList();
      private  SQL con = null;
    
    public Bookscontroller() throws ClassNotFoundException, SQLException{
        // initializing SQL Connection
        this.con = new SQL("librarymanagement");
    }
    
    
     public DoublyLinkList get() throws SQLException{
         
         this.BooksTable.clear();
          ResultSet rs= this.con.select("select * from book");
               while(rs.next()){
                   Book c = new Book();
              
                   c.setID(rs.getString("bk_id"));
                   c.setTitle(rs.getString("bk_title"));
                   c.setCat_id(rs.getString("cat_id"));
                   c.setEdition(rs.getString("bk_edition"));
                   c.setyearOfPublication(rs.getString("year_publication"));
                   
                   
                   this.BooksTable.insertAtFirst(c);
               }
         
         
          return this.BooksTable;
     
     }
    
    public void LoadbooksTable(JTable jTable1){
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
              model1.setRowCount(0);
              
                 DefaultListModel<String> l1 = new DefaultListModel<>();  
        
              this.BooksTable.find((res)->{
                  Book c = (Book) res;        
                 Object[] row = { c.getTitle() ,c.getCatId() ,c.getpublication() , c.getID() };
                 DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                 model.addRow(row);
                 l1.addElement(c.getTitle());
              });
       
              
    }
    
    
    
    
    
    
}
