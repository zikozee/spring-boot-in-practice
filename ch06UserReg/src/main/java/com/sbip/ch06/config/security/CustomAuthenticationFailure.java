package com.sbip.ch06.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Component
public class CustomAuthenticationFailure implements AuthenticationFailureHandler {

    private final DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof DisabledException){
            defaultRedirectStrategy.sendRedirect(request, response, "/login-disabled");
            return;
        }
        defaultRedirectStrategy.sendRedirect(request, response, "/login-error");
    }
}
