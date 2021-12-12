package com.felix.message.repository;

import com.felix.message.entity.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class MockRepository {
  private String request = "";
  @Autowired private CallServiceRepository callServiceRepository;

  private static String accept(ClientHttpRequest clientHttpRequest) {
    System.out.println("paso 3" + clientHttpRequest.getNativeRequest());

    return clientHttpRequest.getNativeRequest().toString();
  }

  public void callMock(UUID uuid) {

    String response;

    WebClient.RequestHeadersSpec<?> client =
        WebClient.create("http://localhost:8080").get().uri("/hola?id={id}", uuid);
    client.httpRequest(clientHttpRequest -> request = accept(clientHttpRequest));
    Mono<String> stringMono =
        client.exchangeToMono(
            clientResponse -> {
              return clientResponse.bodyToMono(String.class);
            });

    response = stringMono.block();

    CallService callService = new CallService();
    callService.setRequest(request);
    callService.setResponse(response);
    callServiceRepository.save(callService);
  }



}
