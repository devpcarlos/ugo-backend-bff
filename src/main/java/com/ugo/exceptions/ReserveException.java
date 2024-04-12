package com.ugo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReserveException extends RuntimeException {
    private int ErrorCode;
    private String ErrorMessage;
}
