/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.exception.AuthorNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Gowtham Adithya
 */
public class AuthorDAO {

    private static Map<Integer, Author> authorMap = new ConcurrentHashMap<>();

    private static AtomicInteger idCounter = new AtomicInteger(0);

    static {
        Author a1 = new Author(idCounter.incrementAndGet(), "john", "smith", "very good author");
        Author a2 = new Author(idCounter.incrementAndGet(), "robert", "james", "very average author");
        Author a3 = new Author(idCounter.incrementAndGet(), "will", "affleck", "very nice author");

        authorMap.put(a1.getAuthorId(), a1);
        authorMap.put(a2.getAuthorId(), a2);
        authorMap.put(a3.getAuthorId(), a3);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> getAllAuthors() {

        return new ArrayList<>(authorMap.values());

    }

    public Author getAuthorById(int id) {

        Author author = authorMap.get(id);
        if (author != null) {
            return author;
        } else {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);
        }

    }

    public Author createAuthor(Author author) {

        if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be empty");
        }
        if (author.getLastName() == null || author.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be empty");
        }
        if (author.getBiography() == null || author.getBiography().trim().isEmpty()) {
            throw new InvalidInputException("Biography name cannot be empty");
        }

        author.setAuthorId(idCounter.incrementAndGet());
        authorMap.put(author.getAuthorId(), author);
        return author;

    }

    public Author updateAuthor(int id, Author author) {

        Author existingAuthor = authorMap.get(id);
        if (existingAuthor == null) {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);
        } else {

            if (author.getFirstName() == null || author.getFirstName().trim().isEmpty()) {
                throw new InvalidInputException("First name cannot be empty");
            }
            if (author.getLastName() == null || author.getLastName().trim().isEmpty()) {
                throw new InvalidInputException("Last name cannot be empty");
            }
            if (author.getBiography() == null || author.getBiography().trim().isEmpty()) {
                throw new InvalidInputException("Biography name cannot be empty");
            }

            existingAuthor.setFirstName(author.getFirstName());
            existingAuthor.setLastName(author.getLastName());
            existingAuthor.setBiography(author.getBiography());
            authorMap.put(id, existingAuthor);
            return existingAuthor;

        }

    }

    public Author deleteAuthor(@PathParam("id") int id) {

        Author author = authorMap.remove(id);
        if (author == null) {
            throw new AuthorNotFoundException("Could Not Find Author With ID: " + id);

        } else {
            return author;
        }
    }

}
