package com.informeguaviare.mi_informe_guaviare.infrastructure.rest;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateBossCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateBossUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.CreateEmployeeUseCase;
import com.informeguaviare.mi_informe_guaviare.application.port.in.LoginUseCase;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.*;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper.UserMapperDTO;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.jwt.JwtProvider;
import com.informeguaviare.mi_informe_guaviare.infrastructure.security.mapper.UserDetailsMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final UserMapperDTO userMapperDTO;
    private final CreateBossUseCase createBossUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final LoginUseCase loginUseCase;
    private final UserDetailsMapper userDetailsMapper;

    public AuthController(JwtProvider jwtProvider, UserMapperDTO userMapperDTO,
            CreateBossUseCase createBossUseCase, CreateEmployeeUseCase createEmployeeUseCase,
            UserDetailsMapper userDetailsMapper,
            LoginUseCase loginUseCase) {
        this.jwtProvider = jwtProvider;
        this.userMapperDTO = userMapperDTO;
        this.createBossUseCase = createBossUseCase;
        this.createEmployeeUseCase = createEmployeeUseCase;
        this.userDetailsMapper = userDetailsMapper;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = loginUseCase.login(loginRequest.email(), loginRequest.password());
        UserDetails userDetails = userDetailsMapper.toUserDetails(user);
        String jwt = jwtProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/boss")
    public ResponseEntity<JwtResponse> createBoss(@Valid @RequestBody CreateBossRequest bossRequest) {
        CreateBossCommand command = userMapperDTO.toCreateBossCommand(bossRequest);
        User user = createBossUseCase.createBoss(command);
        String jwt = generateTokenForUser(user);
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.CREATED);

    }

    @PostMapping("/employee")
    public ResponseEntity<JwtResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest employeeRequest) {
        CreateEmployeeCommand command = userMapperDTO.toCreateEmployeeCommand(employeeRequest);
        User user = createEmployeeUseCase.createEmployee(command);
        String jwt = generateTokenForUser(user);
        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.CREATED);

    }

    private String generateTokenForUser(User user) {
        return jwtProvider.generateToken(userDetailsMapper.toUserDetails(user));
    }

}
