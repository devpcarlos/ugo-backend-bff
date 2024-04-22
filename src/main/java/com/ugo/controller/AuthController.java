package com.ugo.controller;

import com.ugo.JWT.IAuthUseCase;
import com.ugo.dto.AuthResponseDto;
import com.ugo.dto.AuthUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class   AuthController {
    private final IAuthUseCase iAuthUseCase;
    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponseDto> signIn(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authUserDto));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<AuthResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(iAuthUseCase.signOut(jwt));
    }
}
