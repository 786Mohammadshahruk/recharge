package com.recharge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "recharge")
@Data
public class RechargeEntity {
    @Id
    private String rechargeId;
    private String number;
    private String providerName;
    private String amount;
}
