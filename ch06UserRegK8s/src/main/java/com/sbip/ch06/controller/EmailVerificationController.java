package com.sbip.ch06.controller;

import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.service.ApplicationUserService;
import com.sbip.ch06.service.impl.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 19 Feb, 2024
 */

@Controller
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService verificationService;
    private final ApplicationUserService userService;

    @GetMapping(path = "verify/mail")
    public String verifyEmail(@RequestParam("id") String id){
//        byte[] actualId = Base64.getDecoder().decode(id.getBytes());

        String username = verificationService.getUsernameForVerificationId(id);
        if(username != null){
            ApplicationUser applicationUser = userService.findByUsername(username);
            applicationUser.setVerified(true);
            userService.save(applicationUser);
            return "redirect:/login-verified";
        }
        return "redirect:/login-error";
    }
}
