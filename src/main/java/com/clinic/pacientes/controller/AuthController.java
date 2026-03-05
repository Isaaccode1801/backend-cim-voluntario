package com.clinic.pacientes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Necessário para o React acessar
public class AuthController {

    // Lê do application.properties ou usa os valores padrão
    @Value("${app.admin.username:admin}")
    private String adminUsername;

    @Value("${app.admin.password:senha123}")
    private String adminPassword;

    // Memória rápida para guardar os tokens ativos (desloga se o servidor
    // reiniciar, o que é seguro)
    public static final Map<String, Boolean> activeTokens = new ConcurrentHashMap<>();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            // Gera um token único e aleatório
            String token = UUID.randomUUID().toString();
            activeTokens.put(token, true);
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciais invalidas"));
    }
}
