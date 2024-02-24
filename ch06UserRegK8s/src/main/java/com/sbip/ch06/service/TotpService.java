package com.sbip.ch06.service;

import com.sbip.ch06.exception.InvalidVerificationCode;
import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.model.TotpDetails;
import com.sbip.ch06.repo.ApplicationUserRepository;
import com.sbip.ch06.repo.TotpRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Service
@RequiredArgsConstructor
public class TotpService {

    private final GoogleAuthenticator googleAuth = new GoogleAuthenticator();
    private final TotpRepository totpRepository;
    private final ApplicationUserRepository userRepository;
    private static final String ISSUER = "CourseTracker";


    @Transactional
    public String generateAuthenticationQrUrl(String username){
        GoogleAuthenticatorKey authenticationKey = googleAuth.createCredentials();
        String secret = authenticationKey.getKey();
        totpRepository.deleteByUsername(username);
        totpRepository.save(new TotpDetails(username, secret));
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(ISSUER, username, authenticationKey);
    }

    public boolean isTotpEnabled(String username) {
        return userRepository.findApplicationUserByUsername(username).orElse(null).isTotpEnabled();
    }

    public void enableTotpForUser(String username, int code){
        if(!verifyCode(username, code)) {
            throw new InvalidVerificationCode("Invalid verification code");
        }

        ApplicationUser user = userRepository.findApplicationUserByUsername(username).orElse(null);
        user.setTotpEnabled(true);
        userRepository.save(user);
    }

    public boolean verifyCode(String userName, int verificationCode) {
        TotpDetails totpDetails = totpRepository.findByUsername(userName)
                .orElse(null);
        if(totpDetails != null) return googleAuth.authorize(totpDetails.getSecret(), verificationCode);
        else return false;
    }
}
