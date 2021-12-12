package com.felix.message.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table
public class CheckMessageNumber implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private UUID uuid;

  private String status;

  private Long phoneNumber;
}
