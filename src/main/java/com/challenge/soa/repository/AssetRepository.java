package com.challenge.soa.repository;

import com.challenge.soa.domain.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findByCode(String code);
    boolean existsByCode(String code);
}
