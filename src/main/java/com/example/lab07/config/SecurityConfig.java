package com.example.lab07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/about", "/login", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/products/new", "/products/edit/**", "/categories/new/**","/categories/edit/**","/categories/**").hasRole("ADMIN")
                    .requestMatchers("/cart/**").hasRole("USER")
                    .anyRequest().permitAll() // produtos e categorias podem ser vistos por qualquer um
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/products", true)
                    .permitAll()
            )
            .logout(logout -> logout.permitAll());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
