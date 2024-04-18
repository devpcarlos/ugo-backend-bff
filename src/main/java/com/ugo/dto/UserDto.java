package com.ugo.dto;

import com.ugo.entitys.Roles;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo del primer apellido no puede estar vacio")
    private String apellidoPaterno;

    @NotBlank(message = "El campo del segundo apellido no puede estar vacio")
    private String apellidoMaterno;

    @NotBlank(message = "El campo correo electronico no puede estar vacio")
    @Email(message = "El campo correo electronico debe ser valido")
    private String email;

    @NotBlank(message = "El campo contraseña no puede estar vacio")
    @Size(min=8, max = 255, message = "La contraseña debe tener entre 8 y 15 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[{}\\]:;',?/*~$^+=<>.]).{8,14}$", message = "Formato de password no válido")
    private String password;

    private LocalDateTime updated;

    @NotNull( message = "El rolId es obligatorio")
    private Long roleId;
}
