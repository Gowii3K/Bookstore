/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.AuthorDAO;

import com.mycompany.bookstore.model.Author;

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
@Path("/authors")
public class AuthorResource {

    private static final AuthorDAO authorDAO = new AuthorDAO();
    private static final Logger logger=Logger.getLogger(AuthorResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors() {
        logger.info("Retrieving All Authors");
        return authorDAO.getAllAuthors();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(@PathParam("id") int id) {

        logger.info("Retrieving Author With ID "+id);
        Author author = authorDAO.getAuthorById(id);
        return Response.ok(author).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Author author) {
        
        logger.info("Creating new Author");
        Author createdAuthor = authorDAO.createAuthor(author);
        return Response.status(Response.Status.CREATED).entity(createdAuthor).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Author author) {

        logger.info("Updating Author With ID "+id);
        Author existingAuthor = authorDAO.updateAuthor(id, author);
        return Response.ok(existingAuthor).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("id") int id) {
        logger.info("Deleting Author With ID "+id);

        Author author = authorDAO.deleteAuthor(id);
        return Response.noContent().build();

    }

}
