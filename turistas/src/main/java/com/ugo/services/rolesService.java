package com.ugo.services;

import com.ugo.repository.rolesRepository;
import com.ugo.entitys.roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rolesService {

    @Autowired
    private rolesRepository rsp;

    public void saveRoles(roles role){
        rsp.save(role);
    }

}
