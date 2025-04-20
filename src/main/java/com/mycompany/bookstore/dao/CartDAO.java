/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.exception.BookNotFoundException;
import com.mycompany.bookstore.exception.CartNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.exception.OutOfStockException;
import com.mycompany.bookstore.model.Book;
import com.mycompany.bookstore.model.Cart;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Gowtham Adithya
 */
public class CartDAO {

    // customer Id and cart
    private static Map<Integer, Cart> cartMap = new ConcurrentHashMap<>();
    private static BookDAO bookDAO = new BookDAO();

    static {

        Cart cart1 = new Cart(1);
        Cart cart2 = new Cart(2);

        cart1.getCartItems().put(1, 10);
        cart1.getCartItems().put(2, 20);
        cart2.getCartItems().put(1, 10);
        cart2.getCartItems().put(2, 20);
        cart1.setTotalPrice(updateCartTotalPrice(cart1.getCartItems()));
        cart2.setTotalPrice(updateCartTotalPrice(cart2.getCartItems()));

        cartMap.put(cart1.getCustomerId(), cart1);
        cartMap.put(cart2.getCustomerId(), cart2);

    }

    public Cart addItemToCart(int id, Cart cart) {

        Cart customerCart;

        if (cartMap.containsKey(id)) {
            customerCart = cartMap.get(id);
        } else {
            customerCart = new Cart(id);
        }
        Map<Integer, Integer> oldItems = customerCart.getCartItems();
        Map<Integer, Integer> newItems = cart.getCartItems();

        for (Integer bookId : newItems.keySet()) {
            Book book = bookDAO.getBookById(bookId);

            int quantity = newItems.get(bookId);
            if (book.getStock() < quantity) {
                throw new OutOfStockException("Request Book with ID " + bookId + " is not available in the request Quantity");
            }
            if (oldItems.get(bookId) == null) {
                oldItems.put(bookId, quantity);
            } else {
                oldItems.put(bookId, oldItems.get(bookId) + quantity);
            }
            book.setStock(book.getStock() - quantity);
        }
        cartMap.put(id, customerCart);
        customerCart.setTotalPrice(updateCartTotalPrice(customerCart.getCartItems()));
        return customerCart;

    }

    public Cart getCart(int id) {
        if (cartMap.containsKey(id)) {
            return cartMap.get(id);
        }
        throw new CartNotFoundException("No Active Cart For this User");

    }

    public void clearCart(int id) {
        cartMap.remove(id);
    }

    public Cart updateItemInCart(int customerId, int bookId, int quantity) {

        Cart customerCart = getCart(customerId);
        Map<Integer, Integer> cartItems = customerCart.getCartItems();
        if (cartItems.containsKey(bookId)) {

            Book book = bookDAO.getBookById(bookId);

            int oldQuantity = cartItems.get(bookId);
            int difference = quantity - oldQuantity;

            if (difference > 0 && book.getStock() < difference) {
                throw new OutOfStockException("Not enough stock to update quantity");
            }

            book.setStock(book.getStock() - difference);

            cartItems.put(bookId, quantity);
            customerCart.setTotalPrice(updateCartTotalPrice(cartItems));

            return customerCart;

        }

        throw new InvalidInputException("Book does not exist in Cart");

    }

    public Cart deleteItemFromCart(int customerId, int bookId) {

        Cart cart = getCart(customerId);
        Map<Integer, Integer> cartItems = cart.getCartItems();
        

        if (cartItems.containsKey(bookId)) {
            try {
                int quantity = cartItems.get(bookId);
                Book book = bookDAO.getBookById(bookId);
                book.setStock(book.getStock() + quantity);
                cartItems.remove(bookId);
            } catch (BookNotFoundException e) {
                cartItems.remove(bookId);

            }
            cart.setTotalPrice(updateCartTotalPrice(cartItems));
        } else {
            throw new InvalidInputException("Book does not exist in Cart");

        }
        return cart;

    }

    private static int updateCartTotalPrice(Map<Integer, Integer> itemList) {
        int totalPrice = 0;
        for (Map.Entry<Integer, Integer> entry : itemList.entrySet()) {
            Book book = bookDAO.getBookById(entry.getKey());
            totalPrice += book.getPrice() * entry.getValue();
        }
        return totalPrice;
    }

}
