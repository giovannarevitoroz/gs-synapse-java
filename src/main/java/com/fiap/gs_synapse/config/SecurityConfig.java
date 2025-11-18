package com.fiap.gs_synapse.config;

import com.fiap.gs_synapse.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Desabilita CSRF para suportar JWT
                .csrf(csrf -> csrf.disable())

                // ================== REGRAS DE AUTORIZAÇÃO ==================
                .authorizeHttpRequests(auth -> auth

                        // 🔓 LIBERA HOME
                        .requestMatchers("/", "/home").permitAll()

                        // 🔓 LIBERA LOGIN E REGISTRO
                        .requestMatchers("/auth/login", "/auth/register").permitAll()

                        // 🔓 LIBERA THYMELEAF DO LOGIN
                        .requestMatchers("/login", "/usuarios/novo").permitAll()

                        // 🔓 LIBERA ESTÁTICOS (EVITA ERRO NO RENDER)
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()

                        // 🔒 APENAS ADMIN PARA CRUD DE USUÁRIOS
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        // 🔒 RESTO PRECISA DE AUTH
                        .anyRequest().authenticated()
                )

                // ================== LOGIN FORM (PARA THYMELEAF) ==================
                .formLogin(form -> form
                        .loginPage("/login")                  // sua página login.html
                        .loginProcessingUrl("/login")         // POST do form
                        .defaultSuccessUrl("/", true)         // redireciona para home
                        .permitAll()
                )

                // ================== LOGOUT ==================
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // ================== SESSÃO DESLIGADA (JWT) ==================
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Coloca o filtro JWT antes do filtro padrão do Spring Security
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
