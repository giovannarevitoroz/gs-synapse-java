package com.fiap.gs_synapse.config;

import com.fiap.gs_synapse.config.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; // Importação necessária
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * ✅ CORREÇÃO: Define o Bean PasswordEncoder (BCrypt) que estava faltando.
     * Este bean é exigido pelo AuthenticationManager e pelo RegistroController.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // 🔓 ROTAS PÚBLICAS
                        // Adicionando /registrar para permitir o POST de novos usuários
                        .requestMatchers("/", "/login", "/logout", "/auth/**", "/registrar").permitAll()

                        // 🔓 APENAS CSS
                        .requestMatchers("/css/**").permitAll()

                        // 🔒 ROTAS RESTRITAS
                        .requestMatchers("/home", "/competencias", "/recomendacoes/**", "/bemestar")
                        .hasAnyRole("ADMIN", "USER")

                        // 🔒 QUALQUER OUTRA EXIGE AUTENTICAÇÃO
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("nomeUsuario")
                        .passwordParameter("senhaUsuario")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        // 🔥 JWT FILTRO — só para /api e /auth
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Permite H2-console (frameOptions)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}