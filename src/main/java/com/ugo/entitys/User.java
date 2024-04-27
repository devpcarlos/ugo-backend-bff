package com.ugo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    @Column(name = "email",unique = true)
    private String email;
    private String password;
    private Boolean emailConfirmed;
    private LocalDateTime created;
    private LocalDateTime updated;

    @ManyToOne(targetEntity = Roles.class)
    @JoinColumn(name = "roleId")
    @JsonIgnore
    private Roles roleId;

}
