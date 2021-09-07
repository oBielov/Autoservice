package com.autoservice.service;

import com.autoservice.model.Car;
import com.autoservice.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepo;


    @Override
    public void create(Car car) {
        log.info("CarService in create, {}", car);
        carRepo.save(car);
    }

    @Override
    public void update(Car car) {
        log.info("CarService in update, {}", car);
        carRepo.save(car);
    }

}
