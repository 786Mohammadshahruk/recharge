package com.recharge.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MetaDate {
    private String status;
    private String code;
    private String message;
    private String version;

}
