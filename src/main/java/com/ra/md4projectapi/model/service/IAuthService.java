package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.dto.request.FormLogin;
import com.ra.md4projectapi.model.dto.request.FormRegister;
import com.ra.md4projectapi.model.dto.response.JwtResponse;

public interface IAuthService {
    void register(FormRegister formRegister);
    JwtResponse login(FormLogin formLogin);
}
