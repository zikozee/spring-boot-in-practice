package com.sbip.ch06.customhealthinidicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Map;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 23 Jan, 2024
 */

@Component
public class DogsHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
       try {
           ParameterizedTypeReference<Map<String, String>> reference = new ParameterizedTypeReference<>() {};
           ResponseEntity<Map<String, String>> result = RestClient.create().get()
                   .uri("https://dog.ceo/api/breeds/image/random", reference)
                   .retrieve()
                   .toEntity(new ParameterizedTypeReference<>() {});

           if(result.getStatusCode().is2xxSuccessful() && result.hasBody()){
               return Health.up().withDetails(result.getBody()).build();
           }else {
               return Health.down().withDetail("status", result.getStatusCode()).build();
           }
       }catch (RestClientException ex){
           return Health.down().withException(ex).build();
       }
    }
}
