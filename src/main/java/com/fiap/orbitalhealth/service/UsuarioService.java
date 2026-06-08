package com.fiap.orbitalhealth.service;

import com.fiap.orbitalhealth.dto.LoginRequestDTO;
import com.fiap.orbitalhealth.dto.LoginResponseDTO;
import com.fiap.orbitalhealth.security.JwtService;
import com.fiap.orbitalhealth.dto.UsuarioRequestDTO;
import com.fiap.orbitalhealth.entity.Usuario;
import com.fiap.orbitalhealth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public LoginResponseDTO login(
            LoginRequestDTO dto) {

        Usuario usuario = repository.findByEmail(
                        dto.email())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado"));

        boolean senhaCorreta =
                encoder.matches(
                        dto.senha(),
                        usuario.getSenha()
                );

        if (!senhaCorreta) {
            throw new RuntimeException(
                    "Senha inválida");
        }

        String token =
                jwtService.gerarToken(
                        usuario.getEmail());

        return new LoginResponseDTO(token);
    }
    public void cadastrar(
            UsuarioRequestDTO dto) {

        Usuario usuario = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(
                        encoder.encode(
                                dto.senha()
                        )
                )
                .role("USER")
                .build();

        repository.save(usuario);
    }
}