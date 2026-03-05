package com.clinic.pacientes.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenAuthFilter> authFilter() {
        FilterRegistrationBean<TokenAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenAuthFilter());

        // Aplica o filtro SOMENTE nas rotas de pacientes
        // A rota /api/auth/login fica livre para a pessoa tentar logar
        registrationBean.addUrlPatterns("/api/pacientes/*", "/api/pacientes");

        registrationBean.setOrder(1); // É a primeira barreira a ser verificada
        return registrationBean;
    }
}
