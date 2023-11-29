package com.example.productservice.clients.authenticationclient.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidateTokenRequestDTO {
    private Long userId;
    private String token;
}
