package com.sbip.ch06.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedhandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .requiresChannel(channel ->
                        channel
                                .anyRequest()
                                .requiresSecure()
                ) // enforcing ssl
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login", "/webjars/**", "/images/**", "/css/**", "/h2-console/**")
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
                //Using httpBasic in place of form login above  (this opens a dialogue box)
//                .httpBasic(Customizer.withDefaults())

                .build();
    }

// moved up as this is not recommended
    /**
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                .requestMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**");

    }
     */

    /**
    @Bean
    public UserDetailsService userDetailsService(){
        var uds = User.withUsername("user")
                .passwordEncoder(passwordEncoder::encode)
//                .password(passwordEncoder.encode("password"))
                .password("password")
                .roles("USER")
                .build();

        var uds2 = User.withUsername("admin")
                .passwordEncoder(passwordEncoder::encode)
                .password("password")
//                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(uds, uds2);
    }
        */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    //LDAP config
    // using ldap in place of UserDetailsService  see (CustomUserDetailService :: as bean annotation i.e @Service is commented out)
    /**
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=people")
                .contextSource()
                .url("ldap://localhost:8389/dc=manning,dc=com")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
    }
    */
}
