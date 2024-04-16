package com.ugo.JWT;

import com.ugo.dto.AuthUserDto;
import com.ugo.dto.AuthResponseDto;

public interface IAuthUseCase {
   AuthResponseDto signIn(AuthUserDto authUserDto);

    AuthResponseDto signOut(String jwt);
}
