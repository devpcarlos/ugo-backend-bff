package com.ugo.services;

import com.ugo.dto.UserDto;
import com.ugo.entitys.User;
import com.ugo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    UsersRepository usersRepository;

    public ResponseEntity<?> Login(String password, String email){

        //verificamos si el correo exite
       boolean emailExist = usersRepository.existsByEmail(email);

       if (!emailExist){
           return ResponseEntity.badRequest().body("El email no existe ");
       }

       //obtenemos el correo de la base de datos
       User user = usersRepository.findByEmail(email);

       //Encriptamos la contraseña proporcionada por el usuario
       String passwordEncrypt = new BCryptPasswordEncoder().encode(password);

       //verificar si la contraseña es igual a la de base de datos
       if (!user.getPassword().equals((passwordEncrypt))){
           return ResponseEntity.badRequest().body("Contraseña incorrecta");
       }
        // Si las credenciales son válidas, devolver una respuesta exitosa
        return ResponseEntity.ok("Login exitoso para el usuario " + email);
    }

}
