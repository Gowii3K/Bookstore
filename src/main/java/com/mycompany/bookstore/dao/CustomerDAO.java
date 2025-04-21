/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookstore.dao;

import com.mycompany.bookstore.exception.CustomerNotFoundException;
import com.mycompany.bookstore.exception.InvalidInputException;
import com.mycompany.bookstore.model.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Gowtham Adithya
 */
public class CustomerDAO {

    private static Map<Integer, Customer> customerMap = new ConcurrentHashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger(0);

    static {
        Customer c1 = new Customer(idCounter.incrementAndGet(), "will", "smith", "1@gmail.com", "pass1");
        Customer c2 = new Customer(idCounter.incrementAndGet(), "henry", "cavill", "2@gmail.com", "pass2");
        Customer c3 = new Customer(idCounter.incrementAndGet(), "pedro", "pascal", "3@gmail.com", "pass3");

        customerMap.put(c1.getCustomerId(), c1);
        customerMap.put(c2.getCustomerId(), c2);
        customerMap.put(c3.getCustomerId(), c3);
    }

    public List<Customer> getAllCustomers() {

        return new ArrayList<>(customerMap.values());

    }

    public Customer getCustomersById(int id) {

        Customer customer = customerMap.get(id);
        if (customer != null) {
            return customer;
        } else {
            throw new CustomerNotFoundException("Could Not Find Customer With ID: " + id);
        }

    }

    public Customer createCustomer(Customer customer) {

        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be empty");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be empty");
        }
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty");
        }
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty");
        }

        customer.setCustomerId(idCounter.incrementAndGet());
        customerMap.put(customer.getCustomerId(), customer);
        return customer;

    }

    public Customer updateCustomer(int id, Customer customer) {

        Customer existingcCustomer = customerMap.get(id);
        if (existingcCustomer == null) {
            throw new CustomerNotFoundException("Could Not Find Customer With ID: " + id);
        } else {

            if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
                throw new InvalidInputException("First name cannot be empty");
            }
            if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
                throw new InvalidInputException("Last name cannot be empty");
            }
            if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
                throw new InvalidInputException("Email cannot be empty");
            }
            if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
                throw new InvalidInputException("Password cannot be empty");
            }

            existingcCustomer.setFirstName(customer.getFirstName());
            existingcCustomer.setLastName(customer.getLastName());
            existingcCustomer.setEmail(customer.getEmail());
            existingcCustomer.setPassword(customer.getPassword());

            customerMap.put(id, existingcCustomer);
            return existingcCustomer;

        }

    }

    public Customer deleteCustomer(int id) {

        Customer customer = customerMap.remove(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Could Not Find Customer With ID: " + id);

        } else {
            return customer;
        }
    }

}
