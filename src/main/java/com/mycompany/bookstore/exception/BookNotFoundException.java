/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception;

/**
 *
 * @author Gowtham Adithya
 */
public class BookNotFoundException extends RuntimeException{
    
    public BookNotFoundException(String message){
        super(message);
    }
    
    
    
}
