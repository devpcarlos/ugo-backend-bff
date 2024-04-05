package com.ugo.repository;

import com.ugo.entitys.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<users, Long> {
}
