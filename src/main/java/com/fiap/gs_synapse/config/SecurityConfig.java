package com.fiap.gs_synapse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Autorização de rotas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll() // rotas públicas
                        .anyRequest().authenticated() // qualquer outra rota precisa de login
                )
                // Configuração do login
                .formLogin(form -> form
                        .loginPage("/login")        // rota da página de login
                        .defaultSuccessUrl("/home", true) // após login vai para /home
                        .permitAll()
                )
                // Configuração do logout
                .logout(logout -> logout
                        .logoutUrl("/logout")       // rota para deslogar
                        .logoutSuccessUrl("/login?logout") // redireciona após logout
                        .permitAll()
                );

        return http.build();
    }

    // PasswordEncoder para teste com senha em plain text
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
