/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.exception;

/**
 *
 * @author Gowtham Adithya
 */
public class AuthorNotFoundException extends RuntimeException{
    
    public AuthorNotFoundException(String message) {
        super(message);
    }
    

}
