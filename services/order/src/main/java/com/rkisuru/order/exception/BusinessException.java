package com.rkisuru.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class BusinessException extends RuntimeException {

    private final String message;
}