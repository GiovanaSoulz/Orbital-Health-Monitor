package com.fiap.orbitalhealth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.fiap.orbitalhealth.dto.LoginRequestDTO;
import com.fiap.orbitalhealth.dto.LoginResponseDTO;
import com.fiap.orbitalhealth.dto.UsuarioRequestDTO;
import com.fiap.orbitalhealth.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticação",
        description = "Endpoints responsáveis pelo cadastro e login de usuários"
)
public class AuthController {

    private final UsuarioService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Cadastrar usuário",
            description = "Cria um novo usuário no sistema"
    )
    public void register(
            @RequestBody UsuarioRequestDTO dto) {

        service.cadastrar(dto);
    }
    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Realiza autenticação e retorna JWT"
    )
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO dto) {

        return service.login(dto);
    }
}
