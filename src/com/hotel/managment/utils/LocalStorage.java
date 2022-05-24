
package com.hotel.managment.utils;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
/**
 *
 * @author admin
 */
public class LocalStorage {
   private Preferences pref = null;
    
    public  LocalStorage(){
        this.pref = Preferences.userNodeForPackage(LocalStorage.class);
    }
    public void set(String key , String Value){
       this.pref.put(key, Value);
    }
    public String get(String key){
    String Output = this.pref.get(key, null);
        return Output;
    }
     public void set(String key , int Value){
       this.pref.putInt(key, Value);
    }
    public int getInt(String key  ){
    int Output = this.pref.getInt(key,-1);
        return Output;
    }
    public String[] AllKeys(){
       try {
           return this.pref.keys();
       } catch (BackingStoreException ex) {
           Logger.getLogger(LocalStorage.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
    
    public void removeKey(String key){
    this.pref.remove(key);
    }
    public void clearAll(){
       try {
           this.pref.removeNode();
       } catch (BackingStoreException ex) {
           Logger.getLogger(LocalStorage.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}