/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author admin
 */
public class MyDate {
 
    
    
     public String getDate(){
    return LocalDate.now().toString();
    }
    public String getDate(String date){
    return LocalDate.parse(date).toString();
    }
    public String getDay(){
    return InttoString(LocalDate.now().getDayOfMonth());
    }
    public String getDay(String date){
    return InttoString(LocalDate.parse(date).getDayOfMonth());
    }
    public String getMonth(){
    return InttoString(LocalDate.now().getMonthValue());
    }
    public String getMonth(String date){
    return InttoString(LocalDate.parse(date).getMonthValue());
    }
    public String getYear(){
    return InttoString(LocalDate.now().getYear());
    }
    public String getYear(String date){
    return InttoString(LocalDate.parse(date).getYear());
    }
    public String convertDateToString(Date date, String format) {
        String dateStr = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            dateStr = df.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateStr;
    }
    public String convertDateToString(Date date) {
        return convertDateToString(date , "dd-MM-YYYY");
    }
    public Date convertStringToDate(String dateStr, String format) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            date = df.parse(dateStr);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return date;
    }
    public Date convertStringToDate(String dateStr) {

        return convertStringToDate(dateStr , "dd-MM-YYYY");
    }
    
    public String getTime(){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
     return dtf.format(now);  
    }
    
    public int DifferenceOfDays(String date1 , String Date2){
          String str =date1;
          String str2 =Date2;
          
      String[] a1 = str.split("-");
      String[] a2 = str2.split("-");
         int StartDay =  Integer.parseInt(a1[0]);
         int StartMonth =  Integer.parseInt(a1[1]);
         int StartYear =  Integer.parseInt(a1[2]);
         int EndDay =  Integer.parseInt(a2[0]);
         int EndMonth =  Integer.parseInt(a2[1]);
         int EndYear =  Integer.parseInt(a2[2]);
         
         
         
         
         int x = EndDay - StartDay;
         
       
         
        x += (EndMonth - StartMonth ) * 30;
        x += (EndYear - StartYear) * 365;
       
        
        
        return x;
      }
    
    
    
    private String InttoString(int i){
       return Integer.toString(i);
    }
}
