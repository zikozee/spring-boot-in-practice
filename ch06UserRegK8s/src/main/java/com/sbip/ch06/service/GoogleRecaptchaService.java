package com.sbip.ch06.service;

import com.sbip.ch06.model.RecaptchaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */

@Service
public class GoogleRecaptchaService {
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify"
        + "?secret={secret}&remoteip={remoteip}&response={response}";
    private final RestClient restClient;

    @Value("${captcha.secret.key}")
    private String secretKey;

    public GoogleRecaptchaService() {
        this.restClient = RestClient.create();
    }

    public RecaptchaDto verify(String ip, String recaptchaResponse){
        Map<String, String> request = new HashMap<>();
        request.put("remoteip", ip);
        request.put("secret", secretKey);
        request.put("response", recaptchaResponse);

        Map<String, Object> body = restClient.get()
                .uri(VERIFY_URL, request)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        boolean success = (Boolean)body.get("success");
        RecaptchaDto recaptchaDto;
        if(!success) recaptchaDto = new RecaptchaDto(false, (List)body.get("error-codes"));
        else recaptchaDto = new RecaptchaDto(true, Collections.emptyList());

        return recaptchaDto;
    }
}
