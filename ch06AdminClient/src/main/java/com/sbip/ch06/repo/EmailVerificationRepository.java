package com.sbip.ch06.repo;

import com.sbip.ch06.model.EmailVerification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

public interface EmailVerificationRepository extends CrudRepository<EmailVerification, String> {

    boolean existsByUsername(String username);
    Optional<EmailVerification> findByUsername(String username);
}
