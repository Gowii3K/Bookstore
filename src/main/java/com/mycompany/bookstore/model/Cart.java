/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Gowtham Adithya
 */
public class Cart {
    
    private int customerId;
    private Map<Integer,Integer> cartItems = new ConcurrentHashMap<>();

    public Cart(int customerId) {
        this.customerId=customerId;
        
    }
    
    public Cart(){}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, Integer> cartItems) {
        this.cartItems = cartItems;
    }
    
    
    
    

}
