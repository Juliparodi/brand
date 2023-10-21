package com.ecommerce.brand.controller;

import com.ecommerce.brand.model.dto.PricesDTO;
import com.ecommerce.brand.service.IPricesService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/pricing")
@Validated
public class PricesController {

    private final IPricesService pricesService;

    @Autowired
    public PricesController(IPricesService pricesService) {
        this.pricesService = pricesService;
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricesDTO> getPriceDTO(@RequestParam LocalDateTime applicationDate,
                                                 @RequestParam @NotNull Long productId,
                                                 @RequestParam @NotNull Long brandId) {

        PricesDTO pricesDTO = pricesService.getPriceDTO(applicationDate, productId, brandId);
        return ResponseEntity.ok(pricesDTO);
    }
}
