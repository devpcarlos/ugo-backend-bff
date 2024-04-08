package com.ugo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerException extends RuntimeException {

    private int statusCode;
    private String message;
}
