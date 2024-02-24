package com.sbip.ch06.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if(isTotpAuthRequired(authentication)) {
            defaultRedirectStrategy.sendRedirect(request, response, "/totp-login");
        }else {
            defaultRedirectStrategy.sendRedirect(request, response, "/account");
        }
    }

    private boolean isTotpAuthRequired(Authentication authentication){
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        return authorities.contains("TOTP_AUTH_AUTHORITY");
    }
}
