package com.hotel.managment;

import com.hotel.managment.View.UsersView.Home;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        try {
            Home h =new Home();
            h.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
