package com.sbip.ch06.userdetails;

import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.repo.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

//@Service  // --> disabled for ldap
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final ApplicationUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApplicationUser> applicationUserByUsername = repository.findApplicationUserByUsername(username);
        if(applicationUserByUsername.isEmpty())
            throw new UsernameNotFoundException("No user with " + username + " exists in the system");

        ApplicationUser applicationUser = applicationUserByUsername.get();

        return User.builder()
                .username(applicationUser.getUsername())
                .password(applicationUser.getPassword())
                .disabled(!applicationUser.isVerified())
                // this can be used to force password reset after a time
                // we can throw password expired after a period (a column we monitor)
                .accountExpired(applicationUser.isAccountCredentialsExpired())
                // we can use number of incorrect logins by having a counter that reset to Zero on successfully login but increment on wrong login
                //when admin unlocks, it resets back to 0
                .accountLocked(applicationUser.isLocked())
                .roles("USER")
                .build();

    }
}
