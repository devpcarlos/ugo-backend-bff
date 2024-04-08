package com.ugo.services;

import com.ugo.repository.RolesRepository;
import com.ugo.entitys.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rsp;

    public void saveRoles(Roles role){
        rsp.save(role);
    }

}
