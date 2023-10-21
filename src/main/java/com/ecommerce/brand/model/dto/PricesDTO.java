package com.ecommerce.brand.model.dto;

import com.ecommerce.brand.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricesDTO {

    private Integer priceList;

    private Long productId;

    private BigDecimal price;

    private Brand brand;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
