/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.BookDAO;
import com.mycompany.bookstore.dao.CartDAO;
import com.mycompany.bookstore.dao.OrderDAO;
import com.mycompany.bookstore.model.Cart;
import com.mycompany.bookstore.model.Order;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gowtham Adithya
 */

@Path("customers/{customerId}/orders")
public class OrderResource {
    
    
    private static OrderDAO orderDAO= new OrderDAO();
    private static CartDAO cartDAO = new CartDAO();
    
    
    
    @POST
    @Path("items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@PathParam("customerId") int customerId) {
        
        Cart cart= cartDAO.getCart(customerId);
        Order order=orderDAO.createOrder(customerId,cart);
        cartDAO.clearCart(customerId);
        return Response.ok(order).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomerOrders(@PathParam("customerId") int customerId){
        List<Order> orders=orderDAO.getAllCustomerOrders(customerId);
        return Response.ok(orders).build();
        
    }
    
    @GET
    @Path("{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomerOrders(@PathParam("customerId") int customerId,@PathParam("orderId") int orderId ){
        Order order= orderDAO.getOrderById(customerId,orderId);
        return Response.ok(order).build();
        
        
    }
    
    
    
    
}
