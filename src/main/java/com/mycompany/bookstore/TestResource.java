/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Gowtham Adithya
 */
@Path("/test")
public class TestResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTest(){
        return "Hello World";
    }
    
}
