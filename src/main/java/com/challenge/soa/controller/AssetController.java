package com.challenge.soa.controller;

import com.challenge.soa.domain.entity.Asset;
import com.challenge.soa.dto.AssetDTO;
import com.challenge.soa.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService service;
    public AssetController(AssetService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Asset>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Asset> create(@Valid @RequestBody AssetDTO dto) {
        Asset created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/assets/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> update(@PathVariable("id") Long id, @Valid @RequestBody AssetDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<Asset> getByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(
            service.listAll().stream()
                   .filter(a -> a.getCode().equalsIgnoreCase(code))
                   .findFirst()
                   .orElseThrow(() -> new com.challenge.soa.exception.NotFoundException("Asset code " + code + " não encontrado"))
        );
    }
}
