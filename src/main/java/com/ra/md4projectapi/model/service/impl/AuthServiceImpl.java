package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.constants.RoleName;
import com.ra.md4projectapi.model.dto.request.FormLogin;
import com.ra.md4projectapi.model.dto.request.FormRegister;
import com.ra.md4projectapi.model.dto.response.JwtResponse;
import com.ra.md4projectapi.model.entity.Role;
import com.ra.md4projectapi.model.entity.User;
import com.ra.md4projectapi.model.repository.IUserRepository;
import com.ra.md4projectapi.model.service.IAuthService;
import com.ra.md4projectapi.model.service.IRoleService;
import com.ra.md4projectapi.security.jwt.JwtProvider;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public void register(FormRegister formRegister) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        String img = "https://inkythuatso.com/uploads/thumbnails/800/2023/03/6-anh-dai-dien-trang-inkythuatso-03-15-26-36.jpg";
        User user = User.builder()
                .username(formRegister.getUsername())
                .password(passwordEncoder.encode(formRegister.getPassword()))
                .email(formRegister.getEmail())
                .roles(roles)
                .image(img)
                .createdAt(new Date())
                .updatedAt(new Date())
                .isDeleted(false)
                .status(true)
                .build();
        userRepository.save(user);
    }

    @Override
    public JwtResponse login(FormLogin formLogin) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getUsername(), formLogin.getPassword()));
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
        UserDetailCustom userDetail = (UserDetailCustom) authentication.getPrincipal();
        String accessToken = jwtProvider.generateToken(userDetail);
        return JwtResponse.builder()
                .accessToken(accessToken)
                .username(userDetail.getUsername())
                .dob(userDetail.getUsers().getDob())
                .phone(userDetail.getUsers().getPhone())
                .fullName(userDetail.getUsers().getFullName())
                .email(userDetail.getUsers().getEmail())
                .address(userDetail.getUsers().getAddress())
                .status(userDetail.getUsers().getStatus())
                .image(userDetail.getUsers().getImage())
                .roles(userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }
}
