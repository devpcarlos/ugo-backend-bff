package com.ugo.controller;

import com.ugo.dto.RolesDto;
import com.ugo.services.RolesService;
import com.ugo.entitys.Roles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
public class RolesController {

    @Autowired
    private RolesService rs;

    @PostMapping("/save")
    public ResponseEntity<?> saveRoles(@Valid @RequestBody RolesDto role, BindingResult result){

        rs.saveRoles(role, result);
     return ResponseEntity.ok("Rol guardado con exito");

    }

   @GetMapping("/names/{name}")
    public ResponseEntity<?> search(@PathVariable String name){
        return rs.searchRoles(name);
    }

    @GetMapping("/id/{Id}")
    public ResponseEntity<?>findById(@PathVariable Long Id){
       return rs.findById(Id);
    }
}

