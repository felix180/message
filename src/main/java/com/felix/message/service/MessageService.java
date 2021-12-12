package com.felix.message.service;

import com.felix.message.domain.MessageRabbit;
import com.felix.message.domain.Status;

import java.util.UUID;

public interface MessageService {

  String postNumber(Long phoneNumber);

  String sentMessage(UUID code, Status status);

  void checkMessage(MessageRabbit message);
}
