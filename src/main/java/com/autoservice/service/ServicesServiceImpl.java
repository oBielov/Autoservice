package com.autoservice.service;

import com.autoservice.model.ServiceStatus;
import com.autoservice.model.Services;
import com.autoservice.repository.ServicesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServicesServiceImpl implements ServicesService{

    @Autowired
    ServicesRepository servicesRepository;

    @Override
    public Services getById(Long id) {
        return servicesRepository.getById(id);
    }

    @Override
    public void create(Services service) {
        servicesRepository.save(service);
    }

    @Override
    public void update(Services service) {
        servicesRepository.save(service);
    }

    @Override
    public void changeStatus(Long id, ServiceStatus status) {
        Services service = servicesRepository.getById(id);
        service.setStatus(status);
        update(service);
    }
}
