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
        Customer c1 = new Customer(idCounter.incrementAndGet(), "gowii");
        Customer c2 = new Customer(idCounter.incrementAndGet(), "bowii");
        Customer c3 = new Customer(idCounter.incrementAndGet(), "lowii");

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

        if (customer.getCustomerName() == null) {
            throw new InvalidInputException("Author name cannot be empty");

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

            if (customer.getCustomerName() != null) {
                existingcCustomer.setCustomerName(customer.getCustomerName());
            }

            customerMap.put(id, existingcCustomer);
            return customer;

        }

    }

    public Customer deleteCustomer(int id) {
        System.out.println("lols");

        Customer customer = customerMap.remove(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Could Not Find Customer With ID: " + id);

        } else {
            return customer;
        }
    }

}
