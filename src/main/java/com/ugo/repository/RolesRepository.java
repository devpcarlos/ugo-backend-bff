package com.ugo.repository;

import com.ugo.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    boolean existsByRoleName(String name);
    Optional<Roles> findByRoleName(String names);
}