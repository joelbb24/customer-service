package com.jardevs.customers.customerapp.service;

import com.jardevs.customers.customerapp.model.Customer;
import com.jardevs.customers.customerapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerServiceImpl implements CustomerService{

    public CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    public void  saveCustomer(Customer customer) {
        repository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {
        Optional<Customer> customer = repository.findById(id);
        return customer;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    @Override
    public boolean deleteCustomerById(int id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCustomerById(Customer customer, int id) {
        try {
            repository.deleteById(id);
            repository.save(customer);
            return true;
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Something went wrong while updating customer with id --> " + id + " read more --> " + e);
            return false;
        }
    }
}
