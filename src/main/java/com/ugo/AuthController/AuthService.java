package com.ugo.AuthController;

import com.ugo.JWT.IAuthUseCase;
import com.ugo.JWT.JwtAuthenticationProvider;
import com.ugo.dto.AuthResponseDto;
import com.ugo.dto.AuthUserDto;
import com.ugo.dto.UserDto;
import com.ugo.exceptions.CustomerException;
import com.ugo.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthUseCase {
    private final UsersRepository usersRepository;

    /**
     * Clase que administra los JWTs
     */
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * Clase que encripta contraseñas
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Devuelve un dto con el jwt del usuario dadas unas credenciales
     * @param authUserDto Credenciales de acceso
     * @return Dto con el jwt del usuario si las credenciales son validas
     */
    public AuthResponseDto signIn(AuthUserDto authUserDto) {

        Optional<UserDto>User = usersRepository.findByEmail(authUserDto.getEmail());

        if (User.isEmpty()) {
            throw new CustomerException(404,"User do not exist");
        }

        if (!passwordEncoder.matches(authUserDto.getPassword(), User.get().getPassword())) {
            throw new CustomerException(404,"Incorrect password");
        }


        return new AuthResponseDto(jwtAuthenticationProvider.createToken(User.get()));
    }

    @Override

    /**
     * Cierra la sesión eliminando de la lista blanca el token ingresado
     // @param token Token a eliminar
     * @return
     */
    public AuthResponseDto signOut(String token) {

        String[] authElements = token.split(" ");
        return new AuthResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));
    }
}
