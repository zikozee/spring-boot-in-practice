package com.sbip.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Controller
public class TotpController {

    @GetMapping("/totp-login")
    public String totpLogin(){
        return "totp-login";
    }

    @GetMapping("/totp-login-error")
    public String totpLoginError(Model model){
        model.addAttribute("loginError", true);
        return "totp-login";
    }
}
