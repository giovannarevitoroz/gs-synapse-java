package com.fiap.gs_synapse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession // CRÍTICO: Habilita o uso do Redis para armazenar o JSESSIONID
public class SessionConfig {
    // Sua configuração de Redis já está no application.properties, então não precisa de mais nada aqui.
}