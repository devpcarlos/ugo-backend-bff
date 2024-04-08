package com.ugo.repository;

import com.ugo.entitys.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByNombre(String nombre);
}
