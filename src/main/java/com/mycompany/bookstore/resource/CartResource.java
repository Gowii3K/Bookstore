/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.CartDAO;
import com.mycompany.bookstore.model.Cart;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gowtham Adithya
 */
@Path("customers/{customerId}/cart")
public class CartResource {

    private static CartDAO cartDAO = new CartDAO();


    @POST
    @Path("items")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCart(@PathParam("customerId") int customerId, Cart cart) {

        Cart createdCart = cartDAO.addItemToCart(customerId, cart);
        return Response.ok(createdCart).build();

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("customerId")int customerId){
        
        Cart cart= cartDAO.getCart(customerId);
        
        return Response.ok(cart).build();
    
    }
    
    
    @PUT
    @Path("items/{bookId}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItemInCart(@PathParam("customerId")int customerId, @PathParam("bookId") int bookId,int quantity){
        
        Cart updatedCart= cartDAO.updateItemInCart(customerId,bookId,quantity);
        
        return Response.ok(updatedCart).build();
        
    }
    
    
    
    
    @DELETE
    @Path("items/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItemFromCart(@PathParam("customerId")int customerId, @PathParam("bookId") int bookId){
        
        Cart cart= cartDAO.deleteItemFromCart(customerId,bookId);
        
        return Response.ok(cart).build();
    
    }
    
  

}
