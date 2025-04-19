/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.model;

/**
 *
 * @author Gowtham Adithya
 */
public class Book {
    
    private int bookId;
    private String title;
    private int authorId;
    private int publicationYear;
    private String isbn;
    private int price;
    private int stock;

   
    public Book(){}
  

    public Book(int bookId, String title, int authorId, int publicationYear, String isbn, int price, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
    }
    
     public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
    
}
