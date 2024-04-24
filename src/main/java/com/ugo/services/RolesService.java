package com.ugo.services;

import com.ugo.dto.RolesDto;
import com.ugo.repository.RolesRepository;
import com.ugo.entitys.Roles;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rsp;

    public ResponseEntity<?> saveRoles(@Valid @RequestBody RolesDto role, BindingResult result){

        if(result.hasErrors()){
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        if (rsp.existsByRoleName(role.getRoleName())){
            return ResponseEntity.badRequest().body("El rol no existe");
        }


        rsp.save(Roles.builder()
                .roleName(role.getRoleName())
                .build());
        return ResponseEntity.ok("Rol guardado");
    }

    public ResponseEntity<?> searchRoles(String name){
        try {
        Optional<Roles> role = rsp.findByRoleName(name);

        if (role.isEmpty()){
            ResponseEntity.badRequest().body("El role no existe");
        }
            List<RolesDto> Name = role.stream()
                    .map(RoleName ->RolesDto.builder()
                            .roleName(RoleName.getRoleName())
                            .build())
                    .collect(Collectors.toList());
       return ResponseEntity.ok(Name);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuario por nombre: " + e.getMessage());
        }

    }

    public ResponseEntity<?> findById(Long id){
        Optional<Roles> OptionRole = rsp.findById(id);
        if (OptionRole.isPresent()){
            Roles role = OptionRole.get();
            RolesDto rolesDto = RolesDto.builder()
                    .roleName(role.getRoleName())
                    .build();

           return ResponseEntity.ok(rolesDto);
        }else {
            return ResponseEntity.badRequest().body("Error Id");
        }
    }


}
