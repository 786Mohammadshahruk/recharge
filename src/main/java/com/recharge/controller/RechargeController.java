package com.recharge.controller;

import com.recharge.dto.RechargeDto;
import com.recharge.entity.RechargeEntity;
import com.recharge.model.CustomResponseModel;
import com.recharge.model.MetaDate;
import com.recharge.model.ResourceData;
import com.recharge.service.RechargeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping(value = "/perform_recharge")
    public ResponseEntity<CustomResponseModel> performRecharge(@RequestBody RechargeDto rechargeDto) {
        log.info("Request Data : {}", rechargeDto);
        RechargeEntity recharge = rechargeService.performRecharge(rechargeDto);
        return new ResponseEntity<>(getResponse("200", "Success", "Recharge Done", recharge), HttpStatus.CREATED);
    }

    private CustomResponseModel getResponse(String statusCode, String status, String message, RechargeEntity recharge) {
        MetaDate metaDate = MetaDate.builder()
                .code(statusCode).status(status)
                .message(message).version("1.0")
                .build();
        ResourceData resourceData = ResourceData.builder()
                .data(recharge)
                .build();
        return CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<CustomResponseModel> getAllRecharge(@RequestHeader(name = "Auth", required = false) String value) {
        if (value == null || value.isEmpty()) {
            MetaDate metaDate = MetaDate.builder()
                    .code("400").status("BAD REQUEST")
                    .message("BAD REQUEST").version("1.0")
                    .build();
            CustomResponseModel customResponseModel = CustomResponseModel.builder()
                    .metaDate(metaDate)
                    .build();
            return new ResponseEntity<>(customResponseModel, HttpStatus.BAD_REQUEST);
        }
        List<RechargeEntity> rechargeEntities = rechargeService.getAllRecharge();
        MetaDate metaDate = MetaDate.builder()
                .code("200").status("OK")
                .message("Success").version("1.0")
                .build();
        ResourceData resourceData = ResourceData.builder()
                .data(rechargeEntities)
                .build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }
}
