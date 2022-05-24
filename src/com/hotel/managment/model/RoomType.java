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
public class RoomType{
	private String room_type_name;
	private String room_type_id;

	public String getRoom_type_name(){
		return room_type_name;
	}

	public void setRoom_type_name(String room_type_name){
		this.room_type_name=room_type_name;
	}
        	public String getroom_type_id(){
		return room_type_id;
	}

	public void setroom_type_id(String room_type_id){
		this.room_type_id=room_type_id;
	}
}