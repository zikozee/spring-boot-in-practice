package com.sbip.ch06.controller;

import com.sbip.ch06.dto.UserDto;
import com.sbip.ch06.event.UserRegistrationEvent;
import com.sbip.ch06.model.ApplicationUser;
import com.sbip.ch06.model.RecaptchaDto;
import com.sbip.ch06.service.ApplicationUserService;
import com.sbip.ch06.service.GoogleRecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 17 Feb, 2024
 */

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final ApplicationUserService userService;
    private final GoogleRecaptchaService recaptchaService;
    private final ApplicationEventPublisher publisher;

    @Value("${app.email.verification:N}")
    private String emailVerification;


    @GetMapping(path = "adduser")
    public String register(Model model){
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping(path = "adduser")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, HttpServletRequest httpServletRequest){
        if(result.hasErrors()){
            return "add-user";
        }

        String response = httpServletRequest.getParameter("g-recaptcha-response");
        if(response == null) {
            return "add-user";
        }

        String ip = httpServletRequest.getRemoteAddr();
        RecaptchaDto recaptchaDto = recaptchaService.verify(ip, response);
        if(!recaptchaDto.success()) {
            return "redirect:adduser?incorrectCAPTCHA";
        }

        ApplicationUser applicationUser = userService.createUser(userDto);
        if("Y".equalsIgnoreCase(emailVerification)) {
            publisher.publishEvent(new UserRegistrationEvent(applicationUser));
            return "redirect:adduser?validate";
        }
        return "redirect:adduser?success";

    }
}
