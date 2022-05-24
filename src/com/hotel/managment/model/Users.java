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
public class Users{
	private String customer_name;
	private String email;
        private String customer_id;
        private String password;
        
        

	public String getCustomer_name(){
		return customer_name;
	}

	public void setCustomer_name(String customer_name){
		this.customer_name=customer_name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}
	public String getpassword(){
		return password;
	}

	public void setpassword(String password){
		this.password = password;
	}
	public String getcustomer_id(){
		return customer_id;
	}

	public void setcustomer_id(String customer_id){
		this.customer_id = customer_id;
	}
        
        @Override
        public String toString(){
        return "[name="+this.customer_name+", email= "+this.email+", password = "+this.password+"]";
        }
}