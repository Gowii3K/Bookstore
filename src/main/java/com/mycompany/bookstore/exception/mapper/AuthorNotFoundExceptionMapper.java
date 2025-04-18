/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception.mapper;

import com.mycompany.bookstore.exception.AuthorNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Gowtham Adithya
 */
public class AuthorNotFoundExceptionMapper implements ExceptionMapper<AuthorNotFoundException>{
    
     @Override
    public Response toResponse(AuthorNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).
                entity(e.getMessage()).build();
               
    }
    
}
