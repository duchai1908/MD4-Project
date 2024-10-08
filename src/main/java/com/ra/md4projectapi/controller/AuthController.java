package com.ra.md4projectapi.controller;

import com.ra.md4projectapi.constants.EHttpStatus;
import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.FormLogin;
import com.ra.md4projectapi.model.dto.request.FormRegister;
import com.ra.md4projectapi.model.dto.response.ResponseWrapper;
import com.ra.md4projectapi.model.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody FormRegister formRegister) throws DataExistException {
        authService.register(formRegister);
        return new ResponseEntity<>(ResponseWrapper.builder()
                .ehttpStatus(EHttpStatus.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .data("Register successfully")
                .build(),HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody FormLogin formLogin){
        return new ResponseEntity<>(ResponseWrapper.builder()
                .ehttpStatus(EHttpStatus.SUCCESS)
                .statusCode(HttpStatus.CREATED.value())
                .data(authService.login(formLogin))
                .build(), HttpStatus.CREATED);
    }
}
