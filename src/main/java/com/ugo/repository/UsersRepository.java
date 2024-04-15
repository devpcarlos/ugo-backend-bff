package com.ugo.repository;

import com.ugo.dto.UserDto;
import com.ugo.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findByNombre(String nombre);
    boolean existsByEmail(String email);
    User findByEmail(String emial);
}
