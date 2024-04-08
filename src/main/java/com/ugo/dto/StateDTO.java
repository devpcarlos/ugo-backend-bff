package com.ugo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class StateDTO {
    private Long id;
    private String name;
    private Date created;
    private Date updated;
}
