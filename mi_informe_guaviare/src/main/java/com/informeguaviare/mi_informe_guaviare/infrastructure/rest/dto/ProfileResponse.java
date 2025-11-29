package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto;

import com.informeguaviare.mi_informe_guaviare.domain.enums.Role;
import lombok.Data;

@Data
public class ProfileResponse {

    private String name;
    private Role role;

}
