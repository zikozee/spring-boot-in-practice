package com.sbip.ch06.service.impl;

import com.sbip.ch06.dto.UserDto;
import com.sbip.ch06.event.UserRegistrationEvent;
import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.repo.ApplicationUserRepository;
import com.sbip.ch06.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 17 Feb, 2024
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserService implements ApplicationUserService {

    private final ApplicationUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    public ApplicationUser createUser(@NonNull UserDto userDto) {

        ApplicationUser user = new ApplicationUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        log.info("user: {}", user);
        ApplicationUser save = repository.save(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(save));
        return save;
    }

    @Override
    public ApplicationUser findByUsername(@NonNull String username) {
        return repository.findApplicationUserByUsername(username).orElse(null);
    }

    @Override
    public void save(ApplicationUser applicationUser) {
        repository.save(applicationUser);
    }
}
