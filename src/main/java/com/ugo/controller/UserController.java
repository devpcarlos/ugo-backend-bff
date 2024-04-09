package com.ugo.controller;

import com.ugo.dto.UserDto;
import com.ugo.entitys.User;
import com.ugo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userS;

    @PostMapping("/create")
    public ResponseEntity<?>saveUser(@Valid @RequestBody UserDto user, BindingResult result){
       return userS.saveUser(user, result);
    }

    @GetMapping("/all")
    public List<UserDto>listAll(){
        return userS.getAll();
    }

    @GetMapping("/name")
    public ResponseEntity<?> getName( @RequestParam ("name") String name){
       return userS.searchName(name);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
       return userS.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto user, BindingResult result){
        return userS.updateUser(id, user,result);
    }

}
