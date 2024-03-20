package com.raghu.productservice.Repository;

import com.raghu.productservice.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriceRepo extends JpaRepository<Price, UUID> {

    Optional<Price> findByName(String name);
}
