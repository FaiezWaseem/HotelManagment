/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.model;

/**
 *
 * @author admin
 */
public class Employee{
	private String employee_name;
	private String employee_salary;
	private String email;
	private String employee_id;
	private String employee_address;

	public String getEmployee_name(){
		return employee_name;
	}

	public void setEmployee_name(String employee_name){
		this.employee_name=employee_name;
	}

	public String getEmployee_salary(){
		return employee_salary;
	}

	public void setEmployee_salary(String employee_salary){
		this.employee_salary=employee_salary;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmployee_id(){
		return employee_id;
	}

	public void setEmployee_id(String employee_id){
		this.employee_id=employee_id;
	}

	public String getEmployee_address(){
		return employee_address;
	}

	public void setEmployee_address(String employee_address){
		this.employee_address=employee_address;
	}
}