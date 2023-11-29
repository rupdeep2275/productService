package com.example.productservice.clients.authenticationclient;

import com.example.productservice.clients.authenticationclient.dtos.ValidateTokenRequestDTO;
import com.example.productservice.clients.authenticationclient.dtos.ValidateTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthClient {
    private final RestTemplateBuilder restTemplateBuilder;
    public ValidateTokenResponseDTO validate(String token, Long userId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDTO tokenRequestDTO = ValidateTokenRequestDTO.builder()
                .token(token)
                .userId(userId)
                .build();
        ResponseEntity<ValidateTokenResponseDTO> responseEntity = restTemplate.postForEntity("http://localhost:9000/auth/validate",
                tokenRequestDTO, ValidateTokenResponseDTO.class);
        return responseEntity.getBody();
    }
}
