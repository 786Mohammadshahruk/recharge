package com.recharge.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceData<T> {
    private T data;
}
