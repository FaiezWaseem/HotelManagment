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
public class BookingHistory{
	private String room_title;
	private String room_charges;
	private String check_in;
	private String check_out;
	private String customer_id;
	private String history_id;

	public String getRoom_title(){
		return room_title;
	}

	public void setRoom_title(String room_title){
		this.room_title=room_title;
	}

	public String getRoom_charges(){
		return room_charges;
	}

	public void setRoom_charges(String room_charges){
		this.room_charges=room_charges;
	}

	public String getCheck_in(){
		return check_in;
	}

	public void setCheck_in(String check_in){
		this.check_in=check_in;
	}

	public String getCheck_out(){
		return check_out;
	}

	public void setCheck_out(String check_out){
		this.check_out=check_out;
	}

	public String getCustomer_id(){
		return customer_id;
	}

	public void setCustomer_id(String customer_id){
		this.customer_id=customer_id;
	}

	public String getHistory_id(){
		return history_id;
	}

	public void setHistory_id(String history_id){
		this.history_id=history_id;
	}
}