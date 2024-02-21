package com.sbip.ch06.service;

import com.sbip.ch06.dto.UserDto;
import com.sbip.ch06.model.ApplicationUser;
import org.springframework.lang.NonNull;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 17 Feb, 2024
 */


public interface ApplicationUserService {

    ApplicationUser createUser(@NonNull UserDto userDto);
    ApplicationUser findByUsername(@NonNull String username);
    void save(ApplicationUser applicationUser);
}
