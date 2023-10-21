package com.ecommerce.brand.repository;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.brand.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

    @Query("SELECT p FROM Prices p WHERE p.startDate <= :applicationDate " +
            "AND p.endDate >= :applicationDate " +
            "AND p.productId = :productId " +
            "AND p.brand.brandId = :brandId")
    List<Prices> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndProductIdAndBrand(
            LocalDateTime applicationDate, Long productId, Long brandId);
}
