/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.utils;

import com.hotel.managment.utils.MyDate;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author admin
 */
public class Reciept {
      public  void generateReciept(String path , String content) throws IOException{
         FileWriter file2 = new FileWriter(path);
              file2.write(content);
               
            file2.close();
     }   
      public  void generateReciept(String path , String Room , String Charges  ,String Days,  String Total  ) throws IOException{
         FileWriter file2 = new FileWriter(path);
       MyDate d = new MyDate();
              file2.write("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"\n" +
"<head>\n" +
"    <style>\n" +
"        body {\n" +
"            margin: 0;\n" +
"            padding: 0;\n" +
"            font-family: 'PT Sans', sans-serif;\n" +
"            margin: auto;\n" +
"            width: 80%;\n" +
"            display: grid;\n" +
"            place-content: center;\n" +
"            height: 100vh;\n" +
"        }\n" +
"\n" +
"        @page {\n" +
"            size: 2.8in 11in;\n" +
"            margin-top: 0cm;\n" +
"            margin-left: 0cm;\n" +
"            margin-right: 0cm;\n" +
"        }\n" +
"\n" +
"        table {\n" +
"            width: 100%;\n" +
"        }\n" +
"\n" +
"        tr {\n" +
"            width: 100%;\n" +
"\n" +
"        }\n" +
"\n" +
"        h1 {\n" +
"            text-align: center;\n" +
"            vertical-align: middle;\n" +
"        }\n" +
"\n" +
"        #logo {\n" +
"            width: 60%;\n" +
"            text-align: center;\n" +
"            -webkit-align-content: center;\n" +
"            align-content: center;\n" +
"            padding: 5px;\n" +
"            margin: 2px;\n" +
"            display: block;\n" +
"            margin: 0 auto;\n" +
"        }\n" +
"\n" +
"        header {\n" +
"            width: 100%;\n" +
"            text-align: center;\n" +
"            -webkit-align-content: center;\n" +
"            align-content: center;\n" +
"            vertical-align: middle;\n" +
"        }\n" +
"\n" +
"        .items thead {\n" +
"            text-align: center;\n" +
"        }\n" +
"\n" +
"        .center-align {\n" +
"            text-align: center;\n" +
"        }\n" +
"\n" +
"        .bill-details td {\n" +
"            font-size: 12px;\n" +
"        }\n" +
"\n" +
"        .receipt {\n" +
"            font-size: medium;\n" +
"        }\n" +
"\n" +
"        .items .heading {\n" +
"            font-size: 12.5px;\n" +
"            text-transform: uppercase;\n" +
"            border-top:1px solid black;\n" +
"            margin-bottom: 4px;\n" +
"            border-bottom: 1px solid black;\n" +
"            vertical-align: middle;\n" +
"        }\n" +
"\n" +
"        .items thead tr th:first-child,\n" +
"        .items tbody tr td:first-child {\n" +
"            width: 47%;\n" +
"            min-width: 47%;\n" +
"            max-width: 47%;\n" +
"            word-break: break-all;\n" +
"            text-align: left;\n" +
"        }\n" +
"\n" +
"        .items td {\n" +
"            font-size: 12px;\n" +
"            text-align: right;\n" +
"            vertical-align: bottom;\n" +
"        }\n" +
"\n" +
"        .price::before {\n" +
"            \n" +
"            font-family: Arial;\n" +
"            text-align: right;\n" +
"        }\n" +
"\n" +
"        .sum-up {\n" +
"            text-align: right !important;\n" +
"        }\n" +
"        .total {\n" +
"            font-size: 13px;\n" +
"            border-top:1px dashed black !important;\n" +
"            border-bottom:1px dashed black !important;\n" +
"        }\n" +
"        .total.text, .total.price {\n" +
"            text-align: right;\n" +
"        }\n" +
"  \n" +
"        .line {\n" +
"            border-top:1px solid black !important;\n" +
"        }\n" +
"        .heading.rate {\n" +
"            width: 20%;\n" +
"        }\n" +
"        .heading.amount {\n" +
"            width: 25%;\n" +
"        }\n" +
"        .heading.qty {\n" +
"            width: 5%\n" +
"        }\n" +
"        p {\n" +
"            padding: 1px;\n" +
"            margin: 0;\n" +
"        }\n" +
"        section, footer {\n" +
"            font-size: 12px;\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"    <header>\n" +
"        <div id=\"logo\" class=\"media\" data-src=\"logo.png\" src=\"./logo.png\"></div>\n" +
"\n" +
"    </header>\n" +
"    <p>GST Number : 4910487129047124</p>\n" +
"    <table class=\"bill-details\">\n" +
"        <tbody>\n" +
"            <tr>\n" +
"                <td>Date : <span>"+d.getDate()+"</span></td>\n" +
"                <td>Time : <span>"+d.getTime()+"</span></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td>Table #: <span>3</span></td>\n" +
"                <td>Bill # : <span>4</span></td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th class=\"center-align\" colspan=\"2\"><span class=\"receipt\">Original Receipt</span></th>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table>\n" +
"    \n" +
"    <table class=\"items\">\n" +
"        <thead>\n" +
"            <tr>\n" +
"                <th class=\"heading name\">Room</th>\n" +
"                <th class=\"heading qty\">NoOfDays</th>\n" +
"                <th class=\"heading rate\">Charges</th>\n" +
"                <th class=\"heading amount\">Amount</th>\n" +
"            </tr>\n" +
"        </thead>\n" +
"       \n" +
"        <tbody>\n" +
"            <tr>\n" +
"                <td>"+Room+"</td>\n" +
"                <td>"+Days+"</td>\n" +
"                <td class=\"price\">&#36; "+Charges+"</td>\n" +
"                <td class=\"price\">&#36; "+Total+"</td>\n" +
"            </tr>\n" +
"\n" +
"            <tr>\n" +
"                <td colspan=\"3\" class=\"sum-up line\">Subtotal</td>\n" +
"                <td class=\"line price\">&#36; "+Total+"</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td colspan=\"3\" class=\"sum-up\">CGST</td>\n" +
"                <td class=\"price\">&#36; 00.00</td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <th colspan=\"3\" class=\"total text\">Total</th>\n" +
"                <th class=\"total price\">&#36; "+Total+"</th>\n" +
"            </tr>\n" +
"        </tbody>\n" +
"    </table>\n" +
"    <section>\n" +
"        <p>\n" +
"            Paid by : <span>CASH</span>\n" +
"        </p>\n" +
"        <p style=\"text-align:center\">\n" +
"            Thank you for your visit!\n" +
"        </p>\n" +
"    </section>\n" +
"    <footer style=\"text-align:center\">\n" +
"        <p>Technology Partner Technologies</p>\n" +
"        <p>Faiez Waseem , Sameeh Raza , Hamza Shamsi , Umair Khan chandio</p>\n" +
"        <p>szabist</p>\n" +
"    </footer>\n" +
"</body>\n" +
"\n" +
"</html>");
               
            file2.close();
     }   
}
