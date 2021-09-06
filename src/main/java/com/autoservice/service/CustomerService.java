package com.autoservice.service;

import com.autoservice.model.Customer;
import com.autoservice.model.Orders;

import java.util.List;

public interface CustomerService {

    void create(Customer customer);

    void update(Customer customer);

    List<Orders> getOrders(Long id);


}
