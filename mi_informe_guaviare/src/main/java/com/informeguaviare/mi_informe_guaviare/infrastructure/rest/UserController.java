package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateBossCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateBossUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateEmployeeUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateReportUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.IGetUserProfileUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateBossRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateEmployeeRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateReportRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ProfileResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ReportResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.UserResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.UserMapperDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
