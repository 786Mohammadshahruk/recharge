package com.recharge.service;

import com.recharge.dto.RechargeDto;
import com.recharge.entity.RechargeEntity;
import com.recharge.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RechargeService {
    @Autowired
    private RechargeRepository rechargeRepository;
    public RechargeEntity performRecharge(RechargeDto rechargeDto) {
        RechargeEntity recharge = new RechargeEntity();
        recharge.setRechargeId(UUID.randomUUID().toString());

        recharge.setAmount(rechargeDto.getAmount());
        recharge.setNumber(rechargeDto.getNumber());
        recharge.setProviderName(rechargeDto.getProviderName());

        return rechargeRepository.save(recharge);
    }

    public List<RechargeEntity> getAllRecharge() {
        return rechargeRepository.findAll();
    }
}
