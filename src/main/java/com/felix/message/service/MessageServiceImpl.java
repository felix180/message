package com.felix.message.service;

import com.felix.message.domain.MessageRabbit;
import com.felix.message.domain.Status;
import com.felix.message.entity.CheckMessageNumber;
import com.felix.message.entity.MessageNumber;
import com.felix.message.repository.CheckMessageNumberRepository;
import com.felix.message.repository.MessageNumberRepository;
import com.felix.message.repository.MockRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

  public static final String ACTIVE = "ACTIVE";
  private final MessageNumberRepository messageNumberRepository;

  private final MockRepository mockRepository;

  private final RabbitTemplate rabbitTemplate;

  private final CheckMessageNumberRepository checkMessageNumberRepository;

  static final String topicExchangeName = "spring-boot-exchange";

  @Override
  public String postNumber(Long phoneNumber) {

    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    MessageNumber messageNumber = new MessageNumber();

    messageNumber.setPhoneNumber(phoneNumber);
    messageNumber.setDate(offsetDateTime);
    MessageNumber save = messageNumberRepository.save(messageNumber);
    System.out.println("id " + save.getId());

    mockRepository.callMock(messageNumber.getId());

    return "ok";
  }

  @Override
  public String sentMessage(UUID code, Status status) {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(
        topicExchangeName, "foo.bar.hola", new MessageRabbit(code, status));
    return "ok";
  }

  @Override
  public void checkMessage(MessageRabbit message) {

    Optional<MessageNumber> byId = messageNumberRepository.findById(message.getId());

    if (byId.isPresent()) {
      saveData(message, byId.get());
    } else {
      System.out.println("No es un valor valido : " + message.getId());
    }
  }

  private void saveData(MessageRabbit message, MessageNumber messageNumber) {
    if (Status.SUCCESS.equals(message.getStatus())) {
      CheckMessageNumber checkMessageNumber = new CheckMessageNumber();
      checkMessageNumber.setPhoneNumber(messageNumber.getPhoneNumber());
      checkMessageNumber.setStatus(ACTIVE);
      checkMessageNumber.setUuid(messageNumber.getId());
      checkMessageNumberRepository.save(checkMessageNumber);
    } else {
      System.out.println("Como es estado es Fallido para el uuid: " + message.getId());
    }
  }
}
