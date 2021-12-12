package com.felix.message.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table
public class CallService implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String request;

  private String response;
}
