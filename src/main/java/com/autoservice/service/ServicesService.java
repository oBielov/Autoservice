package com.autoservice.service;

import com.autoservice.model.ServiceStatus;
import com.autoservice.model.Services;

public interface ServicesService {

    Services getById(Long id);

    void create(Services service);

    void update(Services service);

    void changeStatus(Long id, ServiceStatus status);

}
