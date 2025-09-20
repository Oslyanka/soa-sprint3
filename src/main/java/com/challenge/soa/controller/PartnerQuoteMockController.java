package com.challenge.soa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/external/quotes")
public class PartnerQuoteMockController {

    @GetMapping("/{code}")
    public ResponseEntity<Map<String, Object>> getQuote(@PathVariable("code") String code) {
        long quoteCents = ThreadLocalRandom.current().nextLong(100_00, 300_00);
        return ResponseEntity.ok(Map.of(
                "code", code,
                "quoteCents", quoteCents,
                "currency", "BRL",
                "source", "PartnerMock"
        ));
    }
}
