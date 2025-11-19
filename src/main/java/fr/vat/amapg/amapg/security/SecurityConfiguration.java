package fr.vat.amapg.amapg.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static fr.vat.amapg.amapg.bootstrap.constant.Route.COMMON_HOMEPAGE;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGIN;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGIN_ERROR;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.LOGOUT;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.REGISTER_MODULE;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.ROOT;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.SHOWCASE;
import static fr.vat.amapg.amapg.bootstrap.constant.Route.SHOWCASE_MODULE;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] publicPaths = {
                ROOT,
                SHOWCASE_MODULE,
                REGISTER_MODULE
        };

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicPaths).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage(LOGIN)
                        .defaultSuccessUrl(COMMON_HOMEPAGE)
                        .failureUrl(LOGIN_ERROR)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl(LOGOUT)
                        .logoutSuccessUrl(SHOWCASE)
                        .permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}