package com.autoservice.service;

import com.autoservice.model.Goods;
import com.autoservice.model.OrderStatus;
import com.autoservice.model.Orders;
import com.autoservice.model.Services;

public interface OrdersService {

    Orders getById(Long id);

    void create(Orders order);

    void update(Orders order);

    void addGoods(Long id, Goods goods);

    void changeStatus(Long id, OrderStatus status);

    void addService(Long id, Services service);

    Double calculateTotal(Long id);

}
