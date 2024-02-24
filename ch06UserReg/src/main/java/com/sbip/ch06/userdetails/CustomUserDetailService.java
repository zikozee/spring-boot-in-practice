package com.sbip.ch06.userdetails;

import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.model.CustomUser;
import com.sbip.ch06.repo.ApplicationUserRepository;
import com.sbip.ch06.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Service  // --> disabled for ldap
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final ApplicationUserRepository repository;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApplicationUser> applicationUserByUsername = repository.findApplicationUserByUsername(username);
        if(applicationUserByUsername.isEmpty())
            throw new UsernameNotFoundException("No user with " + username + " exists in the system");

        if(loginAttemptService.isBlocked(username))
            throw new LockedException("User Account is Locked");

        ApplicationUser appUser = applicationUserByUsername.get();

        SimpleGrantedAuthority simpleGrantedAuthority;
        if(appUser.isTotpEnabled()){
            simpleGrantedAuthority = new SimpleGrantedAuthority("TOTP_AUTH_AUTHORITY");
        }else {
            simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        }

//        UserDetails details = User.builder()
//                .username(appUser.getUsername())
//                .password(appUser.getPassword())
//                .disabled(!appUser.isVerified())
//                // this can be used to force password reset after a time
//                // we can throw password expired after a period (a column we monitor)
//                .accountExpired(appUser.isAccountCredentialsExpired())
//                // we can use number of incorrect logins by having a counter that reset to Zero on successfully login but increment on wrong login
//                //when admin unlocks, it resets back to 0
//                .accountLocked(appUser.isLocked())
////                .roles("USER")
//                .authorities(simpleGrantedAuthority)
//                .build();
//
//        return details;

        CustomUser customUser = new CustomUser(appUser.getUsername(), appUser.getPassword(), appUser.isVerified(),
                !appUser.isAccountCredentialsExpired(), !appUser.isAccountCredentialsExpired(), !appUser.isLocked(), Collections.singletonList(simpleGrantedAuthority));

        customUser.setTotpEnabled(appUser.isTotpEnabled());
        return  customUser;
    }
}
