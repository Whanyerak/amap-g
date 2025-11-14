package fr.vat.amapg.amapg.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/public/**", "/register/**").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                    .loginPage("/auth/login")
                    .failureUrl("/auth/login-error")
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/auth/logout")
                    .permitAll()
                )
                .formLogin(Customizer.withDefaults()
        ).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}