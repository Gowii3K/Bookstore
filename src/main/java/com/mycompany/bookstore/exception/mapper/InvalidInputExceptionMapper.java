/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception.mapper;

import com.mycompany.bookstore.exception.InvalidInputException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Gowtham Adithya
 */
public class InvalidInputExceptionMapper implements ExceptionMapper<InvalidInputException>{

    @Override
    public Response toResponse(InvalidInputException e) {
        return Response.status(Response.Status.BAD_REQUEST).
                entity(e.getMessage()).build();
        
    }
    
    
}
