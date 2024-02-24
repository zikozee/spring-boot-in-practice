package com.sbip.ch06.service.impl;

import com.sbip.ch06.model.EmailVerification;
import com.sbip.ch06.repo.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository repository;

    public String generateVerification(String username) {
        if (!repository.existsByUsername(username)) {
            EmailVerification verification = new EmailVerification(username);
            verification = repository.save(verification);
            return verification.getVerificationId();
        }
        return getVerificationIdByUsername(username);
    }

    public String getVerificationIdByUsername(String username) {
        Optional<EmailVerification> verification = repository.findByUsername(username);
        return verification.map(EmailVerification::getVerificationId).orElse(null);
    }

    public String getUsernameForVerificationId(String verificationId) {
        Optional<EmailVerification> verification =  repository.findById(verificationId);
        return verification.map(EmailVerification::getUsername).orElse(null);
    }
}
