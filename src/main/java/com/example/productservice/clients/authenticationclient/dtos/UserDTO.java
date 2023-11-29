package com.example.productservice.clients.authenticationclient.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class UserDTO {
    private String email;
    private Set<Role> roles;
}
