package com.ra.md4projectapi.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormLogin {
    @NotBlank(message = "Username is blank!")
    private String username;
    @NotBlank(message = "Password is blank")
    private String password;
}
