
package com.hotel.managment.utils;

import java.util.function.Consumer;


public class DoublyLinkList<T>  {
    
    
private Node First; //  Also Called Head  or Front
private Node Last;  // Also Called Tail  or Rear
private int Count;

     public DoublyLinkList(){
      First = null;
      Last = null;
      }

      public boolean isEmpty(){
       return First == null;
      }
    public int Size(){return this.Count;}
    public void insertAtFirst(T item){
      Node n = new Node();
      n.value = item;
      n.next = First;
      n.prev = Last;
      if(isEmpty()){
       Last = n;
       this.Count++;
      }else{
       n.next = First;
       First.prev = n; 
       this.Count++;
      }
      First = n;
      }

    public T getAt(int i){
          if(isEmpty()){
          return null;
        }else{
           Node p = First;
           int c = 0;
           
          while(p != null){
            
              if(c == i){
               return p.value;
             }    
              c++;
             p = p.next;
          
          }
          }
    return null;
    }
    
    public void insertAtLast(T item){
        Node n = new Node();
        n.value = item;
        n.next = null;
        if(isEmpty()){
          First = n;
          this.Count++;
        }else{
              this.Count++;
          Last.next = n;
          n.prev = Last;
        }
         Last = n;
      }
        
      public T DeleteFirstNode(){
       Node p = First;
        if(isEmpty()){
          System.out.println("List Is Empty");
       
        }else if(p.next == null){
        First = null; 
        } else{
        First = p.next;
        First.prev = null;
        } 
      return p.value;
      }
     
      public T DeleteLastNode(){
        if(isEmpty()){
        System.out.println("List is Empty");
            
        }
         Node p = Last;
         Last = Last.prev;
         Last.next = null;
          return p.value;
      }
      
      public void print(){
        if(isEmpty()){
        System.out.println("List is Empty");
        }else{
           Node p = First;
          while(p != null){
             System.out.print(" {"+p.value+"} --> ");     
             p = p.next;
          }
        }
      }
      
      public void clear(){this.First = null; this.Last = null;this.Count = 0;}

      public void find( Consumer callback ){
      if(isEmpty()){
          callback.accept(null);
        }else{
           Node p = First;  
          while(p != null){
            callback.accept(p.value);
             p = p.next;
          
          }
          }
      }
     
      
//node
    class Node
    {
        T value;
        private Node next;
        private Node prev;
    }    
}



