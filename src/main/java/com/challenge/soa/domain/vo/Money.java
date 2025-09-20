package com.challenge.soa.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
    @Column(name = "price_cents", nullable = false)
    private Long amountInCents;
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    protected Money() {}

    public Money(Long amountInCents, String currency) {
        if (amountInCents == null || amountInCents < 0) throw new IllegalArgumentException("amountInCents >= 0");
        if (currency == null || currency.isBlank()) throw new IllegalArgumentException("currency obrigatório");
        this.amountInCents = amountInCents;
        this.currency = currency;
    }

    public Long getAmountInCents() { return amountInCents; }
    public String getCurrency() { return currency; }
}
