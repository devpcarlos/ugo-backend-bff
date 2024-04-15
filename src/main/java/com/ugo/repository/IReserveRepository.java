package com.ugo.repository;

import com.ugo.entitys.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReserveRepository extends JpaRepository<Reserve,Long> {
}
