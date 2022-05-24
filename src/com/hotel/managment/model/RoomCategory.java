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
public class RoomCategory{
	private String room_cat_name;
	private String room_cat_id;

	public String getRoom_cat_name(){
		return room_cat_name;
	}

	public void setRoom_cat_name(String room_cat_name){
		this.room_cat_name=room_cat_name;
	}
	public String getRoom_cat_id(){
		return room_cat_id;
	}

	public void setRoom_cat_id(String room_cat_id){
		this.room_cat_id=room_cat_id;
	}
}