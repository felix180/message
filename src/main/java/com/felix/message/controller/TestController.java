package com.felix.message.controller;

import com.felix.message.domain.Status;
import com.felix.message.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TestController {

  @Autowired RabbitTemplate rabbitTemplate;

  @Autowired private MessageService messageService;

  @PostMapping("/number")
  public @ResponseBody ResponseEntity<String> postNumber(
      @RequestParam("phoneNumber") Long phoneNumber) {

    return ResponseEntity.ok(messageService.postNumber(phoneNumber));
  }

  @PostMapping("/sentMessage")
  public @ResponseBody ResponseEntity<String> postNumber(
      @RequestParam("code") UUID code, @RequestParam("status") Status status) {

    return ResponseEntity.ok(messageService.sentMessage(code, status));
  }
}
