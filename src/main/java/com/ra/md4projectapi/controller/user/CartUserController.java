package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.model.dto.request.CartItemRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/cart")
@RequiredArgsConstructor
public class CartUserController {
    @PostMapping()
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest cartItemRequest, @AuthenticationPrincipal UserDetailCustom userDetailCustom){
        System.out.println(userDetailCustom.getUsers().getFullName());
        return new ResponseEntity<>(new ResponseDtoSuccess<>(), HttpStatus.CREATED);
    }

}
