/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.CustomerDAO;

import com.mycompany.bookstore.model.Customer;
import java.util.List;
import java.util.logging.Logger;

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
@Path("customers")
public class CustomerResource {

    private static CustomerDAO customerDAO = new CustomerDAO();
    private static final Logger logger = Logger.getLogger(CustomerResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {

        logger.info("Retrieving All Author ");
        return customerDAO.getAllCustomers();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersById(@PathParam("id") int id) {

        logger.info("Retrieving Author With ID "+id);
        Customer customer = customerDAO.getCustomersById(id);
        return Response.ok(customer).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        
        logger.info("Creating new Author");

        Customer createdCustomer = customerDAO.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(createdCustomer).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        
        logger.info("Updating Author With ID "+id);
        Customer updatedCustomer = customerDAO.updateCustomer(id, customer);
        return Response.ok(updatedCustomer).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("id") int id) {
        
        logger.info("Deleting Author With ID "+id);

        Customer customer = customerDAO.deleteCustomer(id);
        return Response.noContent().build();

    }

}
