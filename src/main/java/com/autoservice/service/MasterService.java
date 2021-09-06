package com.autoservice.service;

import com.autoservice.model.Master;
import com.autoservice.model.Services;

import java.util.List;

public interface MasterService {

    void create(Master master);

    void update(Master master);

    List<Services> getServices(Long id);

    Double getPayment(Long id);

}
