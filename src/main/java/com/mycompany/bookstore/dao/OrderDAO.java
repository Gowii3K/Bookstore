/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.model.Cart;
import com.mycompany.bookstore.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Gowtham Adithya
 */
public class OrderDAO {

    Map<Integer, List<Order>> orderMap = new ConcurrentHashMap<>();
    private static final AtomicInteger IdCounter = new AtomicInteger(1);
    private static BookDAO bookDAO = new BookDAO();

    public Order createOrder(int customerId, Cart cart) {

        Map<Integer, Integer> itemList = cart.getCartItems();

        

        int orderId = IdCounter.incrementAndGet();

        Order order = new Order(orderId, customerId, itemList, cart.getTotalPrice());
        if(orderMap.containsKey(customerId)){
            orderMap.get(customerId).add(order);
            
        }
        else{
            List<Order> orders= new ArrayList<>();
            orders.add(order);
            orderMap.put(customerId,orders);
            
        
        }
        
        
        return order;

    }
    
    public List<Order> getAllCustomerOrders(int customerId ){
        
        List <Order> orders= orderMap.get(customerId);
        return orders;
        
    }
    
    public Order getOrderById(int customerId, int orderId){
         List <Order> orders= orderMap.get(customerId);
         if(orders!=null){
             for(Order order:orders){
                 if(order.getOrderId()==orderId){
                     return order;
                 }
             }
         }
         return null;
         
    }

}
