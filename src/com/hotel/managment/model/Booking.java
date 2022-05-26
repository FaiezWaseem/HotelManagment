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
public class Booking{
	private String booking_id;
	private String room_id;
	private String customer_id;
	private String check_in_date;
	private String check_out_date;
	private String Room_Title;
	private String Room_Charges;

	public String getRoom_Charges(){
		return Room_Charges;
	}

        
	public void setRoomCharges(String Room_Charges){
		this.Room_Charges = Room_Charges;
	}
	public String getRoom_Title(){
		return Room_Title;
	}

        
	public void setRoom_Title(String Room_Title){
		this.Room_Title = Room_Title;
	}
	public String getbooking_id(){
		return booking_id;
	}

        
	public void setbooking_id(String booking_id){
		this.booking_id = booking_id;
	}


	public void setRoom_id(String room_id){
		this.room_id=room_id;
	}
        
        public String getRoom_id(){
		return room_id;
	}

	public String getCustomer_id(){
		return customer_id;
	}
	
	public void setCustomer_id(String customer_id){
		this.customer_id=customer_id;
	}

	public String getCheck_in_date(){
		return check_in_date;
	}

	public void setCheck_in_date(String check_in_date){
		this.check_in_date=check_in_date;
	}

	public String getCheck_out_date(){
		return check_out_date;
	}

	public void setCheck_out_date(String check_out_date){
		this.check_out_date=check_out_date;
	}
        
        @Override
        public String toString(){
        return "[bookingID : "+this.booking_id+", Room ID : "+this.room_id+", Check In : "+this.check_in_date + ", CheckOut : "+this.check_out_date+" ]";
        }
}
