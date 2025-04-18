/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Book;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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
public class BookDAO {
    
     private static Map<String, Book> bookMap = new ConcurrentHashMap<>();

    static {

        Book b1 = new Book(UUID.randomUUID().toString(), "Book 1", 1, 2000, "000", 5000, 100);
        Book b2 = new Book(UUID.randomUUID().toString(), "Book 2", 1, 2000, "001", 5000, 100);
        Book b3 = new Book(UUID.randomUUID().toString(), "Book 3", 1, 2000, "002", 5000, 100);
        bookMap.put(b1.getBookId(), b1);
        bookMap.put(b2.getBookId(), b2);
        bookMap.put(b3.getBookId(), b3);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {

        return new ArrayList<>(bookMap.values());

    }

 
    public Book getStudentById(@PathParam("id") String id) {

        Book book = bookMap.get(id);
        if (book != null) {
            return book;
        } else {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);
        }

    }

 
    public Book  createBook(Book book) {

        if (book.getAuthorId() == 0) {
            throw new InvalidInputException("Author Does Not Exist");
        }
        if (book.getIsbn() == null) {
            throw new InvalidInputException("ISBN cannot be empty");

        }
        if (book.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than 0");
        }
        if (book.getStock() <= 0) {
            throw new InvalidInputException("Stock must be greater than 0");
        }
        if (book.getTitle() == null) {
            throw new InvalidInputException("Title cannot be empty");
        }

        int year = Year.now().getValue();
        if (book.getPublicationYear() > year) {
            throw new InvalidInputException("Publication Year cannot be in the future");
        }

        String id = UUID.randomUUID().toString();
        book.setBookId(id);
        bookMap.put(id, book);
        return book;

    }

    public Book updateBook(@PathParam("id") String id, Book book) {
        System.out.println("lols");

        Book existingBook = bookMap.get(id);
        if (existingBook == null) {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);
        } else {
            if (book.getAuthorId() != 0) {
                existingBook.setAuthorId(book.getAuthorId());
            }
            if (book.getPrice() != 0) {
                existingBook.setPrice(book.getPrice());
            }
            if (book.getPublicationYear() != 0) {
                existingBook.setPublicationYear(book.getPublicationYear());
            }
            if (book.getStock() != 0) {
                existingBook.setStock(book.getStock());
            }
            if (book.getIsbn() != null) {
                existingBook.setIsbn(book.getIsbn());
            }
            if (book.getTitle() != null) {
                existingBook.setTitle(book.getTitle());
            }

            bookMap.put(id, existingBook);
            return existingBook;

        }

    }

  
    public Book deleteBook(@PathParam("id") String id) {
        System.out.println("lols");

        Book book = bookMap.remove(id);
        if (book == null) {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);

        } else {
            return book;
        }
    }
    
}
