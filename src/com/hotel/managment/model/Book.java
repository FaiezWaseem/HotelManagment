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
public class Book {
    
    
       private String  bk_id ;
       private String  cat_id ;
       private String  bk_title ;
       private String  bk_edition ;
       private String  bk_yearOfPublication ;
       
       
       public void setID(String id)
       { this.bk_id = id;  }
       public void setCat_id(String id)
       { this.cat_id = id;  }
       public void setTitle(String title)
       { this.bk_title = title;  }
       public void setEdition(String edition)
       { this.bk_edition = edition;  }
       public void setyearOfPublication(String yearOfPublication)
       { this.bk_yearOfPublication = yearOfPublication;  }
    
       
       public String getTitle(){return this.bk_title;}
       public String getID(){return this.bk_id;}
       public String getCatId(){return this.cat_id;}
       public String getedition(){return this.bk_edition;}
       public String getpublication(){return this.bk_yearOfPublication;}
}
