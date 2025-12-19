package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.port.in.IGetUserProfileUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ProfileResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.UserMapperDTO;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.CustomUserDetails;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IGetUserProfileUseCase getUserProfileUseCase;
    private final UserMapperDTO userMapperDTO;

    public UserController(UserMapperDTO userMapperDTO, IGetUserProfileUseCase getUserProfileUseCase) {
        this.userMapperDTO = userMapperDTO;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUserId();
        User user = getUserProfileUseCase.getProfile(userId);
        return ResponseEntity.ok(userMapperDTO.toProfile(user));
    }

}
