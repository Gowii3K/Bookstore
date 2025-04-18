/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore;

import com.mycompany.bookstore.resource.AuthorResource;
import com.mycompany.bookstore.resource.BookResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Gowtham Adithya
 */
@ApplicationPath("bookstore")
public class BookStoreApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();
        classes.add(TestResource.class);
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        return classes;
    }

}
