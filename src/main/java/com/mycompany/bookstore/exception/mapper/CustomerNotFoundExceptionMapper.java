/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception.mapper;

import com.mycompany.bookstore.exception.CustomerNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Gowtham Adithya
 */

@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException>{

    @Override
    public Response toResponse(CustomerNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).
                entity(e.getMessage()).build();
    }
    
    
}
