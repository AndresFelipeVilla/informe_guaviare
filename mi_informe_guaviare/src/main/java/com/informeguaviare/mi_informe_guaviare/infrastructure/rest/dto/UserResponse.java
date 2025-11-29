package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import com.informeguaviare.mi_informe_guaviare.domain.enums.Role;
import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String email;
    private String passwordHash;
    private String position;
    private String department;
    private Role role;


}
