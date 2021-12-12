package com.felix.message.repository;

import com.felix.message.entity.CallService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallServiceRepository extends JpaRepository<CallService, Long> {

}
