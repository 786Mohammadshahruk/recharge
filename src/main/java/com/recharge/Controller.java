package com.recharge;

import com.recharge.dto.RechargeDto;
import com.recharge.model.CustomResponseModel;
import com.recharge.model.MetaDate;
import com.recharge.model.ResourceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller {


    @PostMapping(value = "/perform_recharge")
    public ResponseEntity<CustomResponseModel> performRecharge(@RequestBody RechargeDto rechargeDto) {
        log.info("Request Data : {}", rechargeDto);
        return new ResponseEntity<>(getResponse("200", "Success", "Recharge Done", rechargeDto), HttpStatus.CREATED);
    }

    private CustomResponseModel getResponse(String statusCode, String status, String message, RechargeDto rechargeDto) {
        MetaDate metaDate = MetaDate.builder()
                .code(statusCode).status(status)
                .message(message).version("1.0")
                .build();
        ResourceData resourceData = ResourceData.builder()
                .data(rechargeDto)
                .build();
        return CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();
    }
}
