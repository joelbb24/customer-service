package com.jardevs.customers.customerapp.service;

import com.jardevs.customers.customerapp.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void saveCustomer(Customer customer);
    Optional<Customer> findCustomerById(int id);
    List<Customer> findAllCustomers();
    boolean deleteCustomerById(int id);
    boolean updateCustomerById(Customer customer,int id);
}
