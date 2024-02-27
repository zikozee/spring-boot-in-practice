package com.sbip.ch06.event;

import com.sbip.ch06.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 20 Feb, 2024
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        try {
            User user = (User) event.getAuthentication().getPrincipal();
            loginAttemptService.loginSuccess(user.getUsername());
        }catch (ClassCastException ex){
            log.error(ex.getMessage(), ex);
        }
    }
}
