package com.autoservice.rest;

import com.autoservice.model.Goods;
import com.autoservice.model.OrderStatus;
import com.autoservice.model.Orders;
import com.autoservice.model.Services;
import com.autoservice.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v01/orders")
@Tag(name = "Заказы", description = "Работа с базой заказов")
public class OrderRestController {

    @Autowired
    OrdersService ordersService;

    @Operation(summary = "Создание нового заказа")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> saveOrder(@RequestBody Orders order){
        if(order == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.ordersService.create(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующего заказа")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders order){
        if(order == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.ordersService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "Добавление товара к заказу")
    @RequestMapping(value = "{id}/goods", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> addGoodsToOrder(@PathVariable("id") Long id, Goods goods){
        if(id == null || goods == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Orders order = this.ordersService.getById(id);
        if(order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.ordersService.addGoods(id, goods);
        this.ordersService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "Добавление услуг к заказу")
    @RequestMapping(value = "{id}/services", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> addServiceToOrder(@PathVariable("id") Long id, Services service){
        if(id == null || service == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Orders order = this.ordersService.getById(id);
        if(order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.ordersService.addService(id, service);
        this.ordersService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "Изменение статуса заказа")
    @RequestMapping(value = "{id}/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> changeOrderStatus(@PathVariable("id") Long id, OrderStatus status){
        if(id != null || status == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Orders order = this.ordersService.getById(id);
        if(order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.ordersService.changeStatus(id, status);
        this.ordersService.update(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @Operation(summary = "Расчёт и получение стоимости заказа")
    @RequestMapping(value = "{id}/cost", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateOrderCost(@PathVariable("id") Long id){
        if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Double total = this.ordersService.calculateTotal(id);
        return total == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(total, HttpStatus.OK);
    }

}
