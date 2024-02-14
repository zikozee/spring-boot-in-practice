package com.sbip.ch05.config.security;

import com.sbip.ch05.userdetails.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedhandler;
//    private final CustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login")
                                .permitAll()
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login.loginPage("/login"))
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(customAccessDeniedhandler)
                                .accessDeniedPage("/index")
                )

                .build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .requestMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**");

    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        var uds = User.withUsername("user")
//                .passwordEncoder(passwordEncoder::encode)
////                .password(passwordEncoder.encode("password"))
//                .password("password")
//                .roles("USER")
//                .build();
//
//        var uds2 = User.withUsername("admin")
//                .passwordEncoder(passwordEncoder::encode)
//                .password("password")
////                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(uds, uds2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
