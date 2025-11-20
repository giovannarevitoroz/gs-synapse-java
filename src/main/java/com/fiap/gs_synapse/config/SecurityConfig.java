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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // Resolve o problema de bean ausente (resolvido na etapa anterior)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ✅ CORREÇÃO: Define um HttpFirewall personalizado para permitir o ponto e vírgula (;)
     * na URL, necessário para o jsessionid em alguns ambientes de servidor web (como o Tomcat).
     * Isso resolve o erro 'The request was rejected because the URL contained a potentially malicious String ";"'
     */
    @Bean
    public HttpFirewall allowSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        // Permite o ponto e vírgula. Isso é necessário para URLs com jsessionid.
        firewall.setAllowSemicolon(true);
        return firewall;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // 🔓 ROTAS PÚBLICAS
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

        // 🔥 JWT FILTRO
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Permite frameOptions para H2-console (embora não relevante para este erro)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}