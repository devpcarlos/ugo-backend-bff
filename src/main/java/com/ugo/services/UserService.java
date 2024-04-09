package com.ugo.services;


import com.ugo.dto.UserDto;
import com.ugo.entitys.Roles;
import com.ugo.entitys.User;
import com.ugo.repository.RolesRepository;
import com.ugo.repository.UsersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UsersRepository urs;

    @Autowired
    private RolesRepository roles;


    public ResponseEntity<?> saveUser(@Valid UserDto user, BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Roles role = roles.findById(user.getRoleId()).orElse(null);
            if (role == null) {
                // Maneja el caso en que no se encuentra el rol
                return ResponseEntity.badRequest().body("El rol especificado no existe");
            }


        if(urs.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Este email ya hiciste");
        }

        urs.save(User.builder()
                .nombre(user.getNombre())
                .apellidoPaterno(user.getApellidoPaterno())
                .apellidoMaterno(user.getApellidoMaterno())
                .email(user.getEmail())
                .emailConfirmed(false)
                .password(user.getPassword())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .roleId(role)
                        .build()
                );
        return ResponseEntity.ok("El usuario fue guardado");
    }

    public List<UserDto> getAll(){
        List<UserDto> lista= urs.findAll()
                .stream()
                .map(User->UserDto.builder()
                        .nombre(User.getNombre())
                        .apellidoPaterno(User.getApellidoPaterno())
                        .apellidoMaterno(User.getApellidoMaterno())
                        .build())
                .toList();
        if (lista.isEmpty()){
             ResponseEntity.badRequest().body("No se encontraron usuarios en la base de datos");
        }
        return lista;
    }

    public ResponseEntity<?> searchName(String nombre) {
        try {
            List<User> user = urs.findByNombre(nombre);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el nombre proporcionado no existe");
            }

            List<UserDto> dto = user.stream()
                    .map(users -> UserDto.builder()
                    .nombre(users.getNombre())
                    .apellidoPaterno(users.getApellidoPaterno())
                    .apellidoMaterno(users.getApellidoMaterno())
                    .email(users.getEmail())
                    .updated(users.getUpdated())
                    .build())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuario por nombre: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateUser(Long id, @Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessage = result.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        Optional<User> data = urs.findById(id);
        if (!data.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el ID proporcionado no existe");

        }

        User existingUser = data.get();
        existingUser.setNombre(user.getNombre());
        existingUser.setApellidoPaterno(user.getApellidoPaterno());
        existingUser.setApellidoMaterno(user.getApellidoMaterno());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setUpdated(LocalDateTime.now());

        // Obtener el objeto Roles correspondiente al ID proporcionado en el DTO
        Roles role = roles.findById(user.getRoleId()).orElse(null);
        if (role == null) {
            return ResponseEntity.badRequest().body("El rol especificado no existe");
        }

        // Asignar el objeto Roles al usuario actualizado
        existingUser.setRoleId(role);

        urs.save(existingUser);
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
