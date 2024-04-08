package com.ugo.repository;

import com.ugo.entitys.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStateRepository extends JpaRepository<State,Long> {
}