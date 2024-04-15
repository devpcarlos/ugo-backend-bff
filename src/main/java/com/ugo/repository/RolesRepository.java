package com.ugo.repository;

import com.ugo.entitys.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    boolean existsByRoleName(String name);
    List<Roles> findByRoleName(String names);
}