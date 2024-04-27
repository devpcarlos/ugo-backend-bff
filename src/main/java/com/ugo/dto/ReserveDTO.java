package com.ugo.dto;

import com.ugo.entitys.State;
import com.ugo.entitys.User;
import com.ugo.entitys.external.Experience;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    private long Id;
    private Date CreationDate;
    private Date UpdateDate;
    @NotNull
    private String Currency;
    @NotNull
    private int Duration;
    @NotNull
    private int floor;
    @NotNull
    private Long state;
    @NotNull
    private Long ReserveOwner;
    @NotNull
    private Experience experience;
}
