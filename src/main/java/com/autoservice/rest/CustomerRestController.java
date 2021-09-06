package com.autoservice.rest;

import com.autoservice.model.Customer;
import com.autoservice.model.Orders;
import com.autoservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v01/customers")
@Tag(name = "Клиенты", description = "Работа с базой клиентов")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Создание нового клиента")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующего клиента")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех заказов клиента")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orders>> getOrders(@PathVariable("id") Long customerId){
        if(customerId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Orders> orders = this.customerService.getOrders(customerId);
        if(orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
