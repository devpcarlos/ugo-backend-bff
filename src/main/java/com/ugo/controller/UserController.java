package com.ugo.controller;

import com.ugo.entitys.Users;
import com.ugo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserController {

    @Autowired
    private UserService userS;

    public ResponseEntity<?>saveUser(@Valid @RequestBody Users user, BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }
        userS.saveUser(user);
        return ResponseEntity.ok("Los usuarios fueron guardados");
    }
 
}
