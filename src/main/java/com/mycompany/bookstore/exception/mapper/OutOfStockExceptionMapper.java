/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception.mapper;

import com.mycompany.bookstore.exception.OutOfStockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Gowtham Adithya
 */
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException>{

    @Override
    public Response toResponse(OutOfStockException e) {
         return Response.status(Response.Status.CONFLICT).
                entity(e.getMessage()).build();
        
    }
    
    
    
}
