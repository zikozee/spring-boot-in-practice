package com.sbip.ch06.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
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
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof DisabledException){
            defaultRedirectStrategy.sendRedirect(request, response, "/login-disabled");
            return;
        }
        if(exception.getCause() instanceof LockedException){
            defaultRedirectStrategy.sendRedirect(request, response, "/login-locked");
            return;
        }
        defaultRedirectStrategy.sendRedirect(request, response, "/login-error");
    }
}
