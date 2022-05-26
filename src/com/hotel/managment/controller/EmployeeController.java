/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.controller;

import com.hotel.managment.View.AdminView.EmpoyeeScreen;
import com.hotel.managment.View.AdminView.menu5;
import com.hotel.managment.model.Employee;
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
public class EmployeeController {
    
    private SQL con = null;
    private DoublyLinkList<Employee> EmployeeList = new DoublyLinkList();
    private EmpoyeeScreen View;
    private menu5 HomeEmployee;

    public EmployeeController(EmpoyeeScreen View) throws ClassNotFoundException, SQLException {
        this.con = new SQL("hotelmanagement");
        this.View = View;
        this.View.addEmp.addActionListener(e -> {
            try {
                AddNewEmp();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } );
    }
    public EmployeeController(menu5 view) throws ClassNotFoundException, SQLException {
        this.con = new SQL("hotelmanagement"); 
        this.HomeEmployee = view;
        this.loadEmployee();
        this.RenderTable();
        this.HomeEmployee.remove_employee.addActionListener(e -> {
            try {
                RemoveEmployee();
            } catch (SQLException ex) {
                show(ex);
            }
        });
    }

    
    
    public void AddNewEmp() throws SQLException{
        if(!isInputFieldsEmpty()){
           String Param = "('"+this.View.fullname.getText()+"','"+this.View.jComboBox1.getSelectedItem()+"','"+this.View.salary.getText()+"','"+this.View.email.getText()+"')"; 
          boolean res = this.con.insert("INSERT INTO `employee`(`employee_name`, `jobTitle`, `employee_salary`, `email`) VALUES"+Param);
          this.con.insert("INSERT INTO `employee_details`(`employee_id`, `employee_address`) VALUES ('"+this.getLastId()+"','"+this.View.myaddress.getText()+"')");
           show(res ?  "New Employee Added" : "Failed To add new Employee");
        }else{
        show("please fill out all the fields");
        }
    }
    
    public boolean isInputFieldsEmpty(){
       return (this.View.email.getText().isEmpty() && this.View.fullname.getText().isEmpty() && this.View.myaddress.getText().isEmpty() && this.View.salary.getText().isEmpty());
    }
    
    public String   getLastId() throws SQLException{
          ResultSet rs =  this.con.select("SELECT * FROM employee WHERE employee_id = (SELECT MAX(employee_id) FROM employee)");
          rs.next();
          
        return rs.getString("employee_id");
    } 
    
    
    
    private void loadEmployee() throws SQLException{
    this.EmployeeList.clear();
    ResultSet rs =  this.con.select("SELECT * FROM employee , employee_details WHERE employee.employee_id = employee_details.employee_id");
        while(rs.next()){
           Employee e = new Employee();
           e.setEmail(rs.getString("email"));
           e.setEmployee_name(rs.getString("employee_name"));
           e.setEmployee_id(rs.getString("employee_id"));
           e.setEmployee_salary(rs.getString("employee_salary"));
           e.setEmployee_address(rs.getString("employee_address"));
           this.EmployeeList.insertAtFirst(e);
        }
    }
    private void RenderTable(){
            DefaultTableModel model1 = (DefaultTableModel) this.HomeEmployee.jTable1.getModel();
              model1.setRowCount(0);
              

        if(EmployeeList.Size() != 0)
        this.EmployeeList.find(e ->{
            Employee c = (Employee) e;
            
            
       Object[] row = { c.getEmployee_id() ,c.getEmployee_name() , c.getEmployee_salary() , c.getEmail() };
                 DefaultTableModel model = (DefaultTableModel) this.HomeEmployee.jTable1.getModel();
                 model.addRow(row);
       
       });
       
       
    }
    
    private void RemoveEmployee() throws SQLException{
    
        String empID = this.HomeEmployee.emp_id.getText();
        
        this.con.delete("DELETE FROM `employee_details` WHERE employee_id ="+empID);
       boolean res = this.con.delete("DELETE FROM `employee` WHERE employee_id ="+empID);
      this.loadEmployee();
      this.RenderTable();
        show(res ? "Employee Record Removed" : "Failed To Remove");
    }
    
          
      public <T> void print(T s){
         System.out.println(s);
      }
      public <T> void show(T s){
        JOptionPane.showMessageDialog(null,s);
      }

    
    
    
    
    
    
}
