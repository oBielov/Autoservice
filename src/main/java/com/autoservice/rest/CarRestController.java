package com.autoservice.rest;

import com.autoservice.model.Car;
import com.autoservice.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v01/cars")
@Tag(name = "Автомобили", description = "Работа с базой автомобилей")
public class CarRestController {

    @Autowired
    CarService carService;

    @Operation(summary = "Добавление нового автомобиля")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        if(car == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.carService.create(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующего в базе автомобиля")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        if(car == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.carService.update(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
