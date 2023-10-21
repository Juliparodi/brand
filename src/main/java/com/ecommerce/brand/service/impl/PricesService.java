package com.ecommerce.brand.service.impl;

import com.ecommerce.brand.exception.PriceNotFoundException;
import com.ecommerce.brand.model.Prices;
import com.ecommerce.brand.model.dto.PricesDTO;
import com.ecommerce.brand.repository.PricesRepository;
import com.ecommerce.brand.service.IPricesService;
import com.superhero.constants.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PricesService implements IPricesService {

    private final PricesRepository pricesRepository;

    @Autowired
    public PricesService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public PricesDTO getPriceDTO(LocalDateTime applicationDate, Long productId, Long brandId) {
        List<Prices> pricesToApply = pricesRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndProductIdAndBrand(
                applicationDate, productId, brandId);
        if (pricesToApply.isEmpty()) {
            throw new PriceNotFoundException(ExceptionConstants.PRINCING_NOT_FOUND);
        } else {
            return getPriceDTOWithHighPriority(pricesToApply);
        }
    }

    private PricesDTO getPriceDTOWithHighPriority(List<Prices> pricesToApply) {
        Optional<Prices> maxPriorityPrice = pricesToApply.stream()
                .max(Comparator.comparing(Prices::getPriority));

        return maxPriorityPrice.map(this::buildPriceDTO).orElse(null);
    }

    private PricesDTO buildPriceDTO(Prices price) {
        return PricesDTO.builder()
                .price(price.getPrice())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .brand(price.getBrand())
                .productId(price.getProductId())
                .build();
    }
}
