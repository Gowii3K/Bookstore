/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.exception.CartNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Cart;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Gowtham Adithya
 */
public class CartDAO {

    // customer Id and cart
    private static Map<Integer, Cart> cartMap = new ConcurrentHashMap<>();

    static {

        Cart cart1 = new Cart(1);
        Cart cart2 = new Cart(2);

        cart1.getCartItems().put(1, 10);
        cart1.getCartItems().put(2, 20);
        cart2.getCartItems().put(1, 10);
        cart2.getCartItems().put(2, 20);

        cartMap.put(cart1.getCustomerId(), cart1);
        cartMap.put(cart2.getCustomerId(), cart2);

    }

    public Cart addItemToCart(int id, Cart cart) {

        Cart customerCart;

        if (cartMap.containsKey(id)) {
            customerCart = cartMap.get(id);
        } else {
            customerCart = new Cart(id);
        }
        Map<Integer, Integer> oldItems = customerCart.getCartItems();
        Map<Integer, Integer> newItems = cart.getCartItems();

        for (Integer bookId : newItems.keySet()) {
            int quantity = newItems.get(bookId);
            if (oldItems.get(bookId) == null) {
                oldItems.put(bookId, quantity);
            } else {
                oldItems.put(bookId, oldItems.get(bookId) + quantity);
            }
        }
        cartMap.put(id, customerCart);
        return customerCart;

    }

    public Cart getCart(int id) {
        if (cartMap.containsKey(id)) {
            return cartMap.get(id);
        }
        throw new CartNotFoundException("Cart Does Not Exist For This User Yet");
 
    }
    
    
     public Cart updateItemInCart(int customerId,int bookId,int quantity){
         
         Cart customerCart= getCart(customerId);
         Map<Integer, Integer> cartItems = customerCart.getCartItems();
         if(cartItems.containsKey(bookId)){
             
             cartItems.put(bookId, quantity);
             return customerCart;
             
         }
         
         throw new InvalidInputException("Book does not exist in Cart");
         
         
         
        
         
        
        
        
    }
    
    public Cart deleteItemFromCart(int customerId, int bookId){
        
        Cart cart= getCart(customerId);
        Map<Integer, Integer> cartItems = cart.getCartItems();
        cartItems.remove(bookId);
        return cart;
        
        
        
    }

}
