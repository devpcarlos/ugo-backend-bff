package com.ugo.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private String email;
    private String password;
    private Boolean emailConfirmed;
    private LocalDateTime created;
    private LocalDateTime  updated;
    @ManyToOne(targetEntity = Roles.class,fetch = FetchType.EAGER)
    @JoinTable(name = "User_Roles", joinColumns = @JoinColumn(name = "User_id"),inverseJoinColumns = @JoinColumn(name = "Rol_id"))
    @JsonIgnore
    private Set<Roles> roleId = new HashSet<>();
    private boolean IsAccountNoTLocked;
    private boolean IsCredentialNOExpired;
    private boolean IsEnable;
    private boolean IsAccountNoTExpired;
}
