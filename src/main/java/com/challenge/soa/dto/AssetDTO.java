package com.challenge.soa.dto;

import com.challenge.soa.domain.enums.AssetClass;
import com.challenge.soa.domain.enums.RiskLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AssetDTO {

    private Long id;

    @NotBlank @Size(max = 16)
    private String code;

    @NotBlank @Size(max = 120)
    private String name;

    @NotNull
    private AssetClass assetClass;

    @NotNull
    private RiskLevel riskLevel;

    @NotNull @Min(0)
    private Long priceInCents;

    @NotBlank @Size(min = 3, max = 3)
    private String currency;

    public AssetDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public AssetClass getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClass assetClass) { this.assetClass = assetClass; }
    public RiskLevel getRiskLevel() { return riskLevel; }
    public void setRiskLevel(RiskLevel riskLevel) { this.riskLevel = riskLevel; }
    public Long getPriceInCents() { return priceInCents; }
    public void setPriceInCents(Long priceInCents) { this.priceInCents = priceInCents; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
