package com.ugo.entitys;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Size(min=8, max = 15, message = "La contraseña debe tener entre 8 y 15 caracteres")
    private String password;

    @NotBlank(message = "El campo confirmar no puede estar vacio")
    private Boolean emailConfirmed;

    private LocalDateTime created;

    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles roleId;

}
