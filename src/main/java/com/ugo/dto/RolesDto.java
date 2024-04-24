package com.ugo.dto;

import com.ugo.entitys.PermissionEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class RolesDto {


    @NotBlank(message = "El rol no puede estar en vacio")
    private String roleName;
    private Set<PermissionEntity>permissionEntities;
}
