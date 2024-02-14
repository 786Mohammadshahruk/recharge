package com.recharge.repository;

import com.recharge.entity.RechargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargeRepository extends JpaRepository<RechargeEntity,String> {
}
