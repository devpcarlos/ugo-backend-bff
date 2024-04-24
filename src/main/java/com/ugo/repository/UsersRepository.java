package com.ugo.repository;

import com.ugo.dto.UserDto;
import com.ugo.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByNombre(String nombre);
    boolean existsByEmail(String email);
}
