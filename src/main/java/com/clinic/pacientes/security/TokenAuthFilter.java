package com.clinic.pacientes.security;

import com.clinic.pacientes.controller.AuthController;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TokenAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Injeta os cabeçalhos de CORS manualmente para garantir
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "*");
        res.setHeader("Access-Control-Max-Age", "3600");

        // Permite que o navegador verifique o CORS sem ser bloqueado (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Pega o token do cabeçalho
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove a palavra "Bearer "

            // Verifica se o token existe na memória do nosso controlador
            if (AuthController.activeTokens.containsKey(token)) {
                chain.doFilter(request, response); // Deixa o React buscar/salvar os dados
                return;
            }
        }

        // Se chegou aqui, bloqueia com erro 401
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        res.getWriter().write("{\"error\": \"Acesso negado. Logue-se primeiro.\"}");
    }
}
