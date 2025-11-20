package com.fiap.gs_synapse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso a páginas de login, registro e recursos estáticos
                        .requestMatchers("/login", "/registrar", "/css/**", "/js/**", "/images/**", "/webjars/**", "/h2-console/**").permitAll()

                        // 🔥 CORREÇÃO: Exige que o usuário tenha o papel ROLE_ADMIN ou ROLE_USER para acessar as rotas protegidas (como /home, que é o defaultSuccessUrl)
                        // Note que a rota "/usuarios" deve ser protegida separadamente se for apenas para ADMIN.
                        .requestMatchers("/home", "/", "/competencias", "/recomendacoes/**", "/bemestar").hasAnyRole("ADMIN", "USER")

                        // Protege a rota /usuarios para que APENAS ADMIN possa acessar (exemplo de rota específica)
                        .requestMatchers("/usuarios").hasRole("ADMIN")

                        // Garante que todas as outras rotas (não listadas acima) exijam autenticação.
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        // Nomes dos campos do formulário (corretos, baseados em login.html)
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

        // Permite o acesso ao console do H2/PostgreSQL durante o desenvolvimento se necessário (para verificar os dados)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }
}