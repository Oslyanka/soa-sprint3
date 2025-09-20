package com.challenge.soa.domain.entity;

import com.challenge.soa.domain.enums.AssetClass;
import com.challenge.soa.domain.enums.RiskLevel;
import com.challenge.soa.domain.vo.Money;
import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String code;

    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="asset_class", nullable = false, length = 30)
    private AssetClass assetClass;

    @Enumerated(EnumType.STRING)
    @Column(name="risk_level", nullable = false, length = 20)
    private RiskLevel riskLevel;

    @Embedded
    private Money price;

    public Asset() {}

    public Asset(String code, String name, AssetClass assetClass, RiskLevel riskLevel, Money price) {
        this.code = code;
        this.name = name;
        this.assetClass = assetClass;
        this.riskLevel = riskLevel;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public AssetClass getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClass assetClass) { this.assetClass = assetClass; }
    public RiskLevel getRiskLevel() { return riskLevel; }
    public void setRiskLevel(RiskLevel riskLevel) { this.riskLevel = riskLevel; }
    public Money getPrice() { return price; }
    public void setPrice(Money price) { this.price = price; }
}
