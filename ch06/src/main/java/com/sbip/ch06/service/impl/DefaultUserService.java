package com.sbip.ch06.service.impl;

import com.sbip.ch06.dto.UserDto;
import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.repo.ApplicationUserRepository;
import com.sbip.ch06.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 17 Feb, 2024
 */

@Service
@RequiredArgsConstructor
public class DefaultUserService implements ApplicationUserService {

    private final ApplicationUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser createUser(@NonNull UserDto userDto) {

        ApplicationUser user = new ApplicationUser();
        user.setFirstName(user.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return repository.save(user);
    }

    @Override
    public ApplicationUser findByUsername(@NonNull String username) {
        return repository.findApplicationUserByUsername(username).orElse(null);
    }
}
