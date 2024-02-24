package com.sbip.ch06.controller;

import com.sbip.ch06.exception.InvalidVerificationCode;
import com.sbip.ch06.model.CustomUser;
import com.sbip.ch06.model.TotpCode;
import com.sbip.ch06.service.TotpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Controller
@ControllerAdvice
@RequiredArgsConstructor
public class AccountController {
    private final TotpService totpService;


    @GetMapping(path = "account")
    public String getAccount(Model model, @AuthenticationPrincipal CustomUser customUser){
        if(customUser != null && !customUser.isTotpEnabled()){
            model.addAttribute("totpEnabled", false);
        }else {
            model.addAttribute("totpEnabled", true);
        }

        return "account";
    }

    @GetMapping("/setup-totp")
    public String getGoogleAuthenticatorQrUrl(Model model, @AuthenticationPrincipal CustomUser customUser) {
        String username = customUser.getUsername();
        boolean isTotp = customUser.isTotpEnabled();

        if (!isTotp) {
            model.addAttribute("qrUrl", totpService.generateAuthenticationQrUrl(username));
            model.addAttribute("code", new TotpCode(null));
            return "account";
        }
        model.addAttribute("totpEnabled", true);
        return "account";
    }
    @PostMapping("/confirm-totp")
    public String confirmGoogleAuthenticatorSetup(Model model, @AuthenticationPrincipal CustomUser customUser, TotpCode totpCode) {
        boolean isTotp = customUser.isTotpEnabled();
        if (!isTotp) {
            try {
                totpService.enableTotpForUser(customUser.getUsername(), Integer.valueOf(totpCode.code()));
            } catch (InvalidVerificationCode ex) {
                model.addAttribute("totpEnabled", customUser.isTotpEnabled());
                model.addAttribute("confirmError", true);
                model.addAttribute("configureTotp", false);
                model.addAttribute("code", new TotpCode(null));
                return "account";
            }

            model.addAttribute("totpEnabled", true);
        }
        customUser.setTotpEnabled(true);
        return "redirect:/logout";
    }

    @ExceptionHandler(InvalidVerificationCode.class)
    public String handleInvalidTOTPVerificationCode(InvalidVerificationCode ex, Model model,
                                                    @AuthenticationPrincipal CustomUser user) {
        boolean userHasTotpEnabled = user.isTotpEnabled();
        model.addAttribute("totpEnabled", userHasTotpEnabled);
        model.addAttribute("confirmError", true);
        model.addAttribute("code", new TotpCode(null));
        return "account";
    }
}
