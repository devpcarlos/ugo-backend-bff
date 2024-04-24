package com.ugo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, name = "role_name")
    private String roleName;
    @OneToMany(targetEntity = User.class, mappedBy = "roleId", fetch = FetchType.LAZY)
    private List<User>users;
    @ManyToMany(targetEntity = PermissionEntity.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Rol_Permissions", joinColumns = @JoinColumn(name = "Rol_id"),inverseJoinColumns = @JoinColumn(name = "Permissions_id"))
    private Set<PermissionEntity> permissionsList = new HashSet<>();
}
