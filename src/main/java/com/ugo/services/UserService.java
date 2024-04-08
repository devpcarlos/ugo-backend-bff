package com.ugo.services;

import com.ugo.entitys.Users;
import com.ugo.repository.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository urs;

    public ResponseEntity<?> saveUser(@Valid Users user, BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }
        urs.save(user);
        return ResponseEntity.ok("El usuario fue guardado");
    }

    public List<Users> getAll(){
        List<Users> lista= urs.findAll();
        if (lista.isEmpty()){
            throw new RuntimeException("No se encontraron usuarios en la base de datos");
        }
        return lista;
    }

    public ResponseEntity<?> getName(String nombre){
        Users user= urs.findByNombre(nombre);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el nombre proporcionado no existe");
        }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> updateUser( Long id, @Valid Users user,  BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Optional<Users> data = urs.findById(id);
        if (!data.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el ID proporcionado no existe");

        }
        user.setId(id);
        urs.save(user);
        return ResponseEntity.ok("El usuario fue actualizado");

    }

    public ResponseEntity<?> deleteById(Long Id){
        if (urs.existsById(Id)){
            urs.deleteById(Id);
            return ResponseEntity.ok("El usuario fue eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el ID proporcionado no existe");
        }
    }

}
