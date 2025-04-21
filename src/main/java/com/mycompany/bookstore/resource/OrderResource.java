/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.BookDAO;
import com.mycompany.bookstore.dao.CartDAO;
import com.mycompany.bookstore.dao.CustomerDAO;
import com.mycompany.bookstore.dao.OrderDAO;
import com.mycompany.bookstore.model.Cart;
import com.mycompany.bookstore.model.Order;
import java.util.List;
import java.util.logging.Logger;

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
    private static CustomerDAO customerDAO = new CustomerDAO();
        private static final Logger logger = Logger.getLogger(OrderResource.class.getName());

    
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@PathParam("customerId") int customerId) {
        
        logger.info("Creating Order from customer "+customerId+" existing cart");
        
        customerDAO.getCustomersById(customerId);
        
        
        Cart cart= cartDAO.getCart(customerId);
        Order order=orderDAO.createOrder(customerId,cart);
        cartDAO.clearCart(customerId);
        return Response.status(Response.Status.CREATED).entity(order).build();

    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomerOrders(@PathParam("customerId") int customerId){
        logger.info("Retreving all Orders from customer "+customerId);
        customerDAO.getCustomersById(customerId);
        List<Order> orders=orderDAO.getAllCustomerOrders(customerId);
        if(orders==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No orders found for Customer with ID "+customerId)
                    .build();
        }
        return Response.ok(orders).build();
        
    }
    
    @GET
    @Path("{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerOrder(@PathParam("customerId") int customerId,@PathParam("orderId") int orderId ){
        logger.info("Retreving Order with ID "+orderId +"from customer "+customerId);
        customerDAO.getCustomersById(customerId);
        Order order= orderDAO.getOrderById(customerId,orderId);
        if(order==null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Order with Id "+orderId+" does not exist")
                    .build();
        }
        return Response.ok(order).build();
        
        
    }
    
    
    
    
}
