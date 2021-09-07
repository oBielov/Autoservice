package com.autoservice.service;

import com.autoservice.model.Master;
import com.autoservice.model.ServiceStatus;
import com.autoservice.model.Services;
import com.autoservice.repository.MasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MasterServiceImpl implements MasterService{

    final double rate = 0.4;

    @Autowired
    MasterRepository masterRepo;


    @Override
    public void create(Master master) {
        log.info("MasterService in create, {}", master);
        masterRepo.save(master);
    }

    @Override
    public void update(Master master) {
        log.info("MasterService in update, {}", master);
        masterRepo.save(master);
    }

    @Override
    public List<Services> getServices(Long id) {
        log.info("MasterService in getOrders, {}", id);
        Master master = masterRepo.getById(id);
        return master.getServices();
    }

    @Override
    public Double getPayment(Long id) {
        log.info("Master service in getPayment, {}", id);
        Master master = masterRepo.getById(id);
        List<Services> services = master.getServices();
        if(services == null){
            return 0.0;
        }
        services.forEach(s -> s.setStatus(ServiceStatus.CLOSED));
        return services.stream()
                .filter(a -> a != null && a.getPrice() != null)
                .mapToDouble(Services::getPrice)
                .sum()*rate;

    }

}
