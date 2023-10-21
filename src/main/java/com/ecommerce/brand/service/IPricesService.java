package com.ecommerce.brand.service;

import com.ecommerce.brand.model.dto.PricesDTO;

import java.time.LocalDateTime;

public interface IPricesService {

    PricesDTO getPriceDTO(LocalDateTime applicationDate, Long productId, Long brandId);
}
