package com.ugo.dto;

import com.ugo.entitys.external.Experience;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class reserveRequestDto{
    @NotNull
    private String currency;
    @NotNull
    private int duration;
    private String reserveDetails;
    private Experience experience;
    private int pax;
}
