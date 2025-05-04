package com.cloud.psynea.config;

import com.cloud.psynea.security.LoginSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class SecurityConfig {

    @Resource
    LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(req -> {

            req.requestMatchers("/auth/login").permitAll();
            req.requestMatchers("auth/register").permitAll();
            req.anyRequest().authenticated();
        });

        http.formLogin(form -> {

            form.loginPage("/auth/login");
            form.successHandler(loginSuccessHandler);
        });

        http.sessionManagement(session -> {

            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
