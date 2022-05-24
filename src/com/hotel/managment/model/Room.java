/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.model;

import com.hotel.managment.utils.DoublyLinkList;
import com.hotel.managment.utils.SQL;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Room{
	private String room_id;
	private String room_cat_id;
	private String room_type_id;
	private String room_charges;
	private String status;
	private String room_title;
	private String room_cat_title;
	private String room_type_title;
        	
  
        
        
        
        
        public Room(){
       
        
        }
        
	public String getRoom_id(){ 
		return room_id;
	}

	public void setRoom_id(String room_id){
		this.room_id = room_id;
	}
	public String getRoom_cat_id(){
		return room_cat_id;
	}

	public void setRoom_cat_id(String room_cat_id){
		this.room_cat_id=room_cat_id;
	}

	public String getRoom_type_id(){
		return room_type_id;
	}

	public void setRoom_type_id(String room_type_id){
		this.room_type_id=room_type_id;
	}

	public String getRoom_charges(){
		return room_charges;
	}

	public void setRoom_charges(String room_charges){
		this.room_charges=room_charges;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status=status;
	}
	public String getRoom_title(){
		return room_title;
	}

	public void setroom_cat_title(String room_cat_title){
		this.room_cat_title = room_cat_title;
	}
        public String getroom_cat_title(){
            return room_cat_title;
        }
        public String getroom_type_title(){
            return room_type_title;
        }
        public void setroom_type_title(String room_type_title){
            this.room_type_title = room_type_title;
        }
       
        
        @Override
        public String toString(){
        return  "["+this.room_id+","+this.room_cat_title+this.room_charges+this.room_type_id+this.status+"]";
        }
       
        
        
        
}
