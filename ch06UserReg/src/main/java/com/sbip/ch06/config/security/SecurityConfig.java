package com.sbip.ch06.config.security;

import com.sbip.ch06.filter.TotpAuthFilter;
import com.sbip.ch06.handler.CustomAccessDeniedHandler;
import com.sbip.ch06.handler.CustomAuthenticationFailureHandler;
import com.sbip.ch06.handler.DefaultAuthenticationSuccessHandler;
import com.sbip.ch06.handler.Oauth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAccessDeniedHandler customAccessDeniedhandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final DefaultAuthenticationSuccessHandler successHandler;
    private final TotpAuthFilter totpAuthFilter;
//    private final CustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .addFilterBefore(totpAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/adduser" ,"/login", "/login-error", "/login-verified",
                                        "/login-disabled", "/verify/mail", "/login-locked", "/setup-totp", "/confirm-totp")
                                .permitAll()
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .requestMatchers("/totp-login", "/totp-login-error").hasAuthority("TOTP_AUTH_AUTHORITY")
                                .anyRequest()
                                .hasRole("USER")
//                                .authenticated()
                )
                .formLogin(login ->
                        login.loginPage("/login")
                                .successHandler(successHandler)
                                .failureHandler(customAuthenticationFailureHandler))
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(customAccessDeniedhandler)
                                .accessDeniedPage("/index")
                )
                .rememberMe(rem -> {
                    rem.key("remember-me-key")
                            .rememberMeCookieName("course-tracker-remember-me"); // note we can choose to use persistent token remember me
                })
                .logout(logout ->
                        logout.deleteCookies("course-tracker-remember-me")
                )
                .oauth2Login(oauth -> {
                    oauth.loginPage("/login")
                            .successHandler(new Oauth2AuthenticationSuccessHandler());
//                            .defaultSuccessUrl("/index");
                })
                //Using httpBasic in place of form login above  (this opens a dialogue box)
//                .httpBasic(Customizer.withDefaults())

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


    //LDAP config
    // using ldap in place of UserDetailsService  see (CustomUserDetailService :: as bean annotation i.e @Service is commented out)
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=people")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=manning,dc=com")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("userPassword");
//    }
}
