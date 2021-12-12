package com.felix.message.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class FindResult {
  private Long productId;
  private Long brandId;
  private Long priceList;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;
  private BigDecimal price;
}
