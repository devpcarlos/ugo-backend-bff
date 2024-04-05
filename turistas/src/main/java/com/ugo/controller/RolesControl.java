package com.ugo.controller;

import com.ugo.services.rolesService;
import com.ugo.entitys.roles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RolesControl {

    @Autowired
    private rolesService rs;

    @PostMapping("/save-roles")
    public ResponseEntity<?> saveRoles(@Valid @RequestBody roles role, BindingResult result){
        if(result.hasErrors()){
            List<String>errorMessage = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }
        rs.saveRoles(role);
     return ResponseEntity.ok("Rol guardado con exito");

    }
}
