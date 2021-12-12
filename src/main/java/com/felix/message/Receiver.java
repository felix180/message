package com.felix.message;

import com.felix.message.domain.MessageRabbit;
import com.felix.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

  @Autowired MessageService messageService;

  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(MessageRabbit message) {
    System.out.println("Received <" + message + ">");
    messageService.checkMessage(message);
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }
}
