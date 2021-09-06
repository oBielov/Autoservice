package com.autoservice.service;

import com.autoservice.model.Goods;
import com.autoservice.model.OrderStatus;
import com.autoservice.model.Orders;
import com.autoservice.model.Services;
import com.autoservice.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public Orders getById(Long id){
        return ordersRepository.getById(id);
    }

    @Override
    public void create(Orders order) {
        log.info("Orders service in create, {}", order);
        order.setStatus(OrderStatus.RECEIVED);
        order.setOpened(LocalDate.now());
        ordersRepository.save(order);
    }

    @Override
    public void update(Orders order) {
        log.info("Orders service in update, {}", order);
        ordersRepository.save(order);
    }

    @Override
    public void addGoods(Long id, Goods goods) {
        log.info("Orders service in addGoods, {}, {}", id, goods);
        ordersRepository.getById(id).getGoods().add(goods);
    }

    @Override
    public void addService(Long id, Services service){
        log.info("Orders service in addService, {}, {}", id, service);
        ordersRepository.getById(id).getServices().add(service);
    }

    @Override
    public void changeStatus(Long id, OrderStatus status){
        Orders order = ordersRepository.getById(id);
        order.setStatus(status);
        if(status.name().equals("COMPLETED") || status.name().equals("FAILED")){
            order.setClosed(LocalDate.now());
        }
    }

    @Override
    public Double calculateTotal(Long id) {
        log.info("Order service in calculateTotal, {}", id);
        return ordersRepository.getById(id).getTotal();
    }
}
