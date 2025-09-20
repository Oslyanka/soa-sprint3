package com.challenge.soa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class QuoteClient {

    private final RestClient rest;

    public QuoteClient(RestClient.Builder builder,
                       @Value("${quotes.base-url:http://localhost:8080/external}") String baseUrl) {
        this.rest = builder.baseUrl(baseUrl).build();
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getQuote(String code) {
        return rest.get()
                .uri("/quotes/{code}", code)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) ->
                        new RuntimeException("Erro ao obter cotação: " + res.getStatusCode()))
                .body(Map.class);
    }
}
