package com.sbip.ch06.listener;

import com.sbip.ch06.event.UserRegistrationEvent;
import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.service.impl.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

//    private final JavaMailSender mailSender;
    private final EmailVerificationService verificationService;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        ApplicationUser user = event.getApplicationUser();
        String username = user.getUsername();
        String verificationId = verificationService.generateVerification(username);
        String email = event.getApplicationUser().getEmail();
        log.info("Listener:: sending email to {}", email );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Course Tracker Account Verification");
        message.setText(getText(user, verificationId));
        message.setTo(email);
//        mailSender.send(message);

    }

    private String getText(ApplicationUser user, String verificationId) {
        String encodedVerificationId = new String(Base64.getEncoder().encode(verificationId.getBytes()));

        return "Dear " + user.getFirstName() + " " +
                user.getLastName() + "," +
                System.lineSeparator() +
                System.lineSeparator() +
                "Your account has been successfully created in the Course Tracker application. " +
                "Activate your account by clicking the following link: http://localhost:8080/verify/email?id=" +
                encodedVerificationId +
                System.lineSeparator() + System.lineSeparator() +
                "Regards," + System.lineSeparator() + "Course Tracker Team";
    }
}
