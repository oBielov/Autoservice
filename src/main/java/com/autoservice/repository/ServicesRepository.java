package com.autoservice.repository;

import com.autoservice.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
