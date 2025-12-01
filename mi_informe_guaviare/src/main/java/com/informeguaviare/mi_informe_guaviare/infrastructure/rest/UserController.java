package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.port.in.IGetUserProfileUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ProfileResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.UserMapperDTO;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String userId) {
        User user = getUserProfileUseCase.getProfile(userId);
        return ResponseEntity.ok(userMapperDTO.toProfile(user));
    }

}
