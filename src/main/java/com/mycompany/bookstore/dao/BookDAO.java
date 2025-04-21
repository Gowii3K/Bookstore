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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Gowtham Adithya
 */
public class BookDAO {

    private static Map<Integer, Book> bookMap = new ConcurrentHashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);

    static {

        Book b1 = new Book(idCounter.incrementAndGet(), "Book 1", 1, 2000, "000", 5000, 100);
        Book b2 = new Book(idCounter.incrementAndGet(), "Book 2", 1, 2000, "001", 5000, 100);
        Book b3 = new Book(idCounter.incrementAndGet(), "Book 3", 1, 2000, "002", 5000, 100);
        bookMap.put(b1.getBookId(), b1);
        bookMap.put(b2.getBookId(), b2);
        bookMap.put(b3.getBookId(), b3);

    }

    public List<Book> getAllBooks() {

        return new ArrayList<>(bookMap.values());

    }

    public Book getBookById(int id) {

        Book book = bookMap.get(id);
        if (book != null) {
            return book;
        } else {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);
        }

    }

    public Book createBook(Book book) {

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

        int id = idCounter.incrementAndGet();
        book.setBookId(id);
        bookMap.put(id, book);
        return book;

    }

    public Book updateBook(int id, Book book) {

        Book existingBook = bookMap.get(id);
        if (existingBook == null) {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);
        }

        existingBook.setAuthorId(book.getAuthorId());

        if (book.getPrice() > 0) {
            existingBook.setPrice(book.getPrice());
        } else {
            throw new InvalidInputException("Price must be greater than 0");
        }

        int year = Year.now().getValue();
        if (book.getPublicationYear() != 0 && book.getPublicationYear() <= year) {
            existingBook.setPublicationYear(book.getPublicationYear());
        }
        else{
            throw new InvalidInputException("Publication year cannot be in the future");
        }

        if (book.getStock() > 1) {
            existingBook.setStock(book.getStock());
        } else {
            throw new InvalidInputException("Stock must be greater than 0");
        }

        if (book.getIsbn() != null && !book.getIsbn().trim().isEmpty()) {
            existingBook.setIsbn(book.getIsbn());
        } else {
            throw new InvalidInputException("ISBN cannot be empty");
        }
        if (book.getTitle() != null && !book.getTitle().trim().isEmpty()) {
            existingBook.setTitle(book.getTitle());
        } else {
            throw new InvalidInputException("Title cannot be empty");
        }

        bookMap.put(id, existingBook);
        return existingBook;

    }

    public Book deleteBook(int id) {

        Book book = bookMap.remove(id);
        if (book == null) {
            throw new BookNotFoundException("Could Not Find Book With ID: " + id);

        } else {
            return book;
        }
    }

}
