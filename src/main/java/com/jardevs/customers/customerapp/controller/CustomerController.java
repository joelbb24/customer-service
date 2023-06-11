package com.jardevs.customers.customerapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jardevs.customers.customerapp.model.Customer;
import com.jardevs.customers.customerapp.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerController {

    CustomerServiceImpl service;

    @Autowired
    public CustomerController(CustomerServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/customers/add-customer")
    public void addCustomer(@RequestBody Customer customer){
        Customer newCustomer = new Customer(customer.customerId,
                                            customer.customerFirstName,
                                            customer.customerLastName,
                                            customer.customerPhoneNumber,
                                            customer.customerEmail,
                                            customer.customerAddress);
        service.saveCustomer(newCustomer);
    }

    @GetMapping("/customers/get-customer/{id}")
    public String getCustomerById(@PathVariable(name = "id") int id) {
        Optional<Customer> customerById = service.findCustomerById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String data = new String();
        if(customerById.isPresent()) {
            try {
                data = objectMapper.writeValueAsString(customerById.get());
            } catch (JsonProcessingException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, "Something went wrong in getCustomerById " + e);
            }
        } else {
            data = "No customer found with the provided id " + id;
        }

        return data;
    }

    @GetMapping("/customers/all-customers")
    public String getAllCustomers() {
        List<Customer> allCustomers = service.findAllCustomers();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(allCustomers);
        } catch (JsonProcessingException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Something went wrong in getAllCustomers " + e);
            return "Network has an issue";
        }
    }

    @DeleteMapping("/customers/delete-customer/{id}")
    public String deleteCustomerById(@PathVariable(name = "id") int id) {
        boolean deleted = service.deleteCustomerById(id);
        if(deleted) {
            return "Customer has been successfully deleted.";
        } else {
            return "Customer does not exist to perform delete operation";
        }
    }

    @PutMapping("/customers/update-customer/{id}")
    public String updateCustomerById(@PathVariable(name = "id") @RequestBody Customer customer, int id) {
        boolean updated = service.updateCustomerById(customer, id);
        if(updated) {
            return "Customer successfully updated!";
        } else {
            return "Issue in updating customer";
        }
    }
}
