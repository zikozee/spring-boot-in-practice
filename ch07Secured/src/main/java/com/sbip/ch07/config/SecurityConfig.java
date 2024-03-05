package com.sbip.ch07.config;

import com.sbip.ch07.model.Course;
import com.sbip.ch07.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 05 Mar, 2024
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //

    @Value("${jwks.uri:http://localhost:9999/oauth2/jwks}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {

        http.oauth2ResourceServer(resourceCustomizer ->
                resourceCustomizer
                        .jwt(jwtCustomizer ->
                                jwtCustomizer
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter)

                        )
        );

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean // ##  make sure to have the role properly configured in your principal from SecurityContextHolder
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
    }

//    @Bean // ##  make sure to have the role properly configured in your principal from SecurityContextHolder
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            List<String> authorities = jwt.getClaim("authorities");
//            return authorities.stream().map(SimpleGrantedAuthority::new).collect(toList());
//        });
//
//        return jwtAuthenticationConverter;
//    }

    @Bean
    BiFunction<Optional<Course>, String, Boolean> getAuthor(){
        return (course, userId) -> course.filter(c -> c.getName().equals(userId)).isPresent();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
        return jwtAuthenticationConverter;
    }

    private static class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Collection<?> authorities = (Collection<?>)
                    jwt.getClaims().getOrDefault("authorities", Collections.emptyList());

            return authorities.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }
}
