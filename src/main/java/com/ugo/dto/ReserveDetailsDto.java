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
public class ReserveDetailsDto {

    private long id;

    private Date creationDate;

    private Date updateDate;

    @NotNull
    private String currency;

    @NotNull
    private int duration;

    @NotNull
    private int floor;

    @NotNull
    private Long state;

    @NotNull
    private Long reserveOwner;

    @NotNull
    private Experience experience;

    private String reserveDetails;

    private int pax;
}
