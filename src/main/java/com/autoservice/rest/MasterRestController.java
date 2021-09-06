package com.autoservice.rest;

import com.autoservice.model.Master;
import com.autoservice.model.Services;
import com.autoservice.service.MasterService;
import com.autoservice.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v01/masters")
@Tag(name = "Мастера", description = "Работа с базой мастеров")
public class MasterRestController {

    @Autowired
    MasterService masterService;
    ServicesService servicesService;

    @Operation(summary = "Добавление в базу нового мастера")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Master> saveMaster(@RequestBody Master master){
        HttpHeaders headers = new HttpHeaders();
        if (master == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.masterService.create(master);
        return new ResponseEntity<>(master, headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующего мастера")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Master> updateMaster(@RequestBody Master master){
        HttpHeaders headers = new HttpHeaders();
        if(master == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.masterService.update(master);
        return new ResponseEntity<>(master, headers, HttpStatus.OK);
    }

    @Operation(summary = "Получение списка услуг конкретного мастера по его id")
    @RequestMapping(value = "{id}/services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Services>> getServices(@PathVariable("id") Long masterId){
        if(masterId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Services> orders = this.masterService.getServices(masterId);
        if(orders == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Расчёт зарплаты мастера по его id")
    @RequestMapping(value = "{id}/payment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getPayment(@PathVariable("id") Long masterId){
        if (masterId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Double payment = this.masterService.getPayment(masterId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }




}
