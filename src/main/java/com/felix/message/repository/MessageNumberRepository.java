package com.felix.message.repository;

import com.felix.message.entity.MessageNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageNumberRepository extends JpaRepository<MessageNumber, UUID> {

}
