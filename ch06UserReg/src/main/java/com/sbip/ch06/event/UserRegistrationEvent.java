package com.sbip.ch06.event;

import com.sbip.ch06.model.ApplicationUser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Slf4j
@Getter
public class UserRegistrationEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = -2685172945219633123L;

    private final ApplicationUser applicationUser;

    public UserRegistrationEvent(ApplicationUser applicationUser) {
        super(applicationUser);
        log.info("EventPublisher Publishing event ...... {}", applicationUser.getUsername());
        this.applicationUser = applicationUser;
    }

}
