/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.resource;

import com.mycompany.bookstore.dao.AuthorDAO;
import com.mycompany.bookstore.dao.BookDAO;

import com.mycompany.bookstore.model.Book;

import java.util.List;

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
@Path("books")
public class BookResource {

    private static BookDAO bookDAO = new BookDAO();
    private static AuthorDAO authorDAO = new AuthorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return bookDAO.getAllBooks();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {

        Book book = bookDAO.getBookById(id);
        return Response.ok(book).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        authorDAO.getAuthorById(book.getAuthorId());
        Book createdBook = bookDAO.createBook(book);
        return Response.status(Response.Status.CREATED).entity(createdBook).build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, Book book) {
        System.out.println("lols");
        Book existingBook = bookDAO.getBookById(id);

        authorDAO.getAuthorById(book.getAuthorId());

        authorDAO.getAuthorById(existingBook.getAuthorId());

        Book updatedBook = bookDAO.updateBook(id, book);

        return Response.ok(updatedBook).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") int id) {

        Book book = bookDAO.deleteBook(id);
        return Response.noContent().build();

    }

}
