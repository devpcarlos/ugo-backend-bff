package com.ugo.repository;

import com.ugo.entitys.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionsRepository extends JpaRepository<PermissionEntity,Long> {
}