package com.felix.message.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@ToString
public class MessageRabbit implements Serializable {

  static final long serialVersionUID = 774617946560886028L;

  private UUID id;
  private Status status;
}
