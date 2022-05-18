/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hotel.managment.lib;

import java.util.Base64;

/**
 *
 * @author admin
 */
public class Base64Encryption {
    
    
    public String Encrypt(String s)
    {
      String encodedString = Base64.getEncoder().encodeToString(s.getBytes());
      return encodedString;
    }
    
    public String Decrypt (String encrypted)
    {
       byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
