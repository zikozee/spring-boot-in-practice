package com.sbip.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(path = "login")
    public String login() {
        return "login";
    }

    @GetMapping(path="login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping(path="login-verified")
    public String loginVerified(Model model) {
        model.addAttribute("loginVerified", true);
        return "login";
    }

    @GetMapping(path="login-disabled")
    public String loginDisabled(Model model) {
        model.addAttribute("loginDisabled", true);
        return "login";
    }

    @GetMapping(path="login-locked")
    public String loginLocked(Model model) {
        model.addAttribute("loginLocked", true);
        return "login";
    }
}
