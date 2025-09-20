package com.challenge.soa.controller;

import com.challenge.soa.domain.entity.Asset;
import com.challenge.soa.dto.AssetQuoteDTO;
import com.challenge.soa.exception.NotFoundException;
import com.challenge.soa.repository.AssetRepository;
import com.challenge.soa.service.QuoteClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetQuoteController {

    private final AssetRepository repository;
    private final QuoteClient quoteClient;

    public AssetQuoteController(AssetRepository repository, QuoteClient quoteClient) {
        this.repository = repository;
        this.quoteClient = quoteClient;
    }

    @GetMapping("/quote/{code}")
    public ResponseEntity<AssetQuoteDTO> quote(@PathVariable("code") String code) {
        Asset asset = repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Asset code " + code + " não encontrado"));

        Map<String, Object> external = quoteClient.getQuote(code);

        AssetQuoteDTO dto = new AssetQuoteDTO();
        dto.assetId = asset.getId();
        dto.code = asset.getCode();
        dto.name = asset.getName();
        dto.assetClass = asset.getAssetClass().name();
        dto.riskLevel = asset.getRiskLevel().name();
        dto.storedPriceInCents = asset.getPrice().getAmountInCents();
        dto.externalQuoteInCents = ((Number) external.get("quoteCents")).longValue();
        dto.currency = String.valueOf(external.get("currency"));
        dto.source = String.valueOf(external.get("source"));
        return ResponseEntity.ok(dto);
    }
}
