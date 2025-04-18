/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.exception.AuthorNotFoundException;

import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
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

    private static Map<Integer, Author> authorMap = new ConcurrentHashMap<>();
    
    private static AtomicInteger idCounter= new AtomicInteger(0);

    static {
        Author a1 = new Author(idCounter.incrementAndGet(), "gowii");
        Author a2 = new Author(idCounter.incrementAndGet(), "bowii");
        Author a3 = new Author(idCounter.incrementAndGet(), "lowii");

        authorMap.put(a1.getAuthorId(), a1);
        authorMap.put(a2.getAuthorId(), a2);
        authorMap.put(a3.getAuthorId(), a3);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors() {

        return new ArrayList<>(authorMap.values());

    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorById(@PathParam("id") int id) {

        Author author = authorMap.get(id);
        if (author != null) {
            return Response.ok(author).build();
        } else {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);
        }

    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Author author) {
        
        if(author.getAuthorName()==null){
            throw new InvalidInputException("Author name cannot be empty");

        }
      
        author.setAuthorId(idCounter.incrementAndGet());
        authorMap.put(author.getAuthorId(), author);
        return Response.ok(author).build();

    }
    
    
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Author author) {
        System.out.println("lols");

        Author existingAuthor = authorMap.get(id);
        if (existingAuthor == null) {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);
        } else {
            
            if (author.getAuthorName()!=null) {
                existingAuthor.setAuthorName(author.getAuthorName());
            }

            authorMap.put(id, existingAuthor);
            return Response.ok(existingAuthor).build();

        }

    }
    
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAuthor(@PathParam("id") int id) {
        System.out.println("lols");

        Author author = authorMap.remove(id);
        if (author == null) {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);

        } else {
            return Response.ok(author).build();
        }
    }

}
