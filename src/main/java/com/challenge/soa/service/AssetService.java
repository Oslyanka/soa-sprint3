package com.challenge.soa.service;

import com.challenge.soa.domain.entity.Asset;
import com.challenge.soa.domain.vo.Money;
import com.challenge.soa.dto.AssetDTO;
import com.challenge.soa.exception.BusinessException;
import com.challenge.soa.exception.NotFoundException;
import com.challenge.soa.repository.AssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AssetService {
    private final AssetRepository repository;
    public AssetService(AssetRepository repository) { this.repository = repository; }

    public List<Asset> listAll() { return repository.findAll(); }

    public Asset getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Asset id " + id + " não encontrado"));
    }

    @Transactional
    public Asset create(AssetDTO dto) {
        if (repository.existsByCode(dto.getCode())) throw new BusinessException("Já existe ativo com código " + dto.getCode());
        Asset asset = toEntity(dto);
        return repository.save(asset);
    }

    @Transactional
    public Asset update(Long id, AssetDTO dto) {
        Asset existing = getById(id);
        if (!existing.getCode().equals(dto.getCode()) && repository.existsByCode(dto.getCode())) {
            throw new BusinessException("Já existe ativo com código " + dto.getCode());
        }
        existing.setCode(dto.getCode());
        existing.setName(dto.getName());
        existing.setAssetClass(dto.getAssetClass());
        existing.setRiskLevel(dto.getRiskLevel());
        existing.setPrice(new Money(dto.getPriceInCents(), dto.getCurrency()));
        return repository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        Asset existing = getById(id);
        repository.delete(existing);
    }

    private Asset toEntity(AssetDTO dto) {
        return new Asset(dto.getCode(), dto.getName(), dto.getAssetClass(), dto.getRiskLevel(),
                new Money(dto.getPriceInCents(), dto.getCurrency()));
    }
}
