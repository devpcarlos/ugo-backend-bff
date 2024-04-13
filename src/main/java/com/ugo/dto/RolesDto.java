package com.ugo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class RolesDto {


    @NotBlank(message = "El rol no puede estar en vacio")
    private String roleName;
}
