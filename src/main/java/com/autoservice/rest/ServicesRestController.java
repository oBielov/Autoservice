package com.autoservice.rest;

import com.autoservice.model.ServiceStatus;
import com.autoservice.model.Services;
import com.autoservice.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v01/services")
@Tag(name = "Услуги", description = "Работа с базой данных услуг")
public class ServicesRestController{

    @Autowired
    ServicesService servicesService;

    @Operation(summary = "Создание новой услуги")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Services> saveService(@RequestBody Services service){
        if(service == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.servicesService.create(service);
        return new ResponseEntity<>(service, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующей услуги")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Services> updateService(@RequestBody Services service){
        if(service == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.servicesService.update(service);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @Operation(summary = "Изменение статуса активной услуги(оплачена/не оплачена)")
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Services> changeServiceStatus(@PathVariable("id") Long id, ServiceStatus status){
        if(id == null || status == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Services service = this.servicesService.getById(id);
        if(service == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.servicesService.changeStatus(id, status);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }



}
