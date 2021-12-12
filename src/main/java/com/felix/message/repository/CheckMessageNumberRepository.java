package com.felix.message.repository;

import com.felix.message.entity.CheckMessageNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckMessageNumberRepository extends JpaRepository<CheckMessageNumber, Long> {}
