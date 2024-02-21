package com.sbip.ch06.controller;

import com.sbip.ch06.dto.UserDto;
import com.sbip.ch06.service.ApplicationUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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


    @GetMapping(path = "adduser")
    public String register(Model model){
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    @PostMapping(path = "adduser")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result){
        if(result.hasErrors()){
            return "add-user";
        }
        userService.createUser(userDto);
        return "redirect:adduser?success";

    }
}
