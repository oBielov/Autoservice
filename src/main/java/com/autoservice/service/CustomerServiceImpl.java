package com.autoservice.service;

import com.autoservice.model.Customer;
import com.autoservice.model.Orders;
import com.autoservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void create(Customer customer) {
        log.info("Customer service in create, {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        log.info("Customer service in update, {}", customer);
    }

    @Override
    public List<Orders> getOrders(Long id) {
        Customer customer = customerRepository.getById(id);
        return customer.getOrders();
    }
}
