package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateBossCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.RequestPasswordResetCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.ValueObjectMapper;
import com.informeguaviare.mi_informe_guaviare.application.command.ResetPasswordCommand;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateBossRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateEmployeeRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ForgotPasswordRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ProfileResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ResetPasswordRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ValueObjectMapper.class })
public interface UserMapperDTO {

    CreateBossCommand toCreateBossCommand(CreateBossRequest request);

    CreateEmployeeCommand toCreateEmployeeCommand(CreateEmployeeRequest request);

    ProfileResponse toProfile(User user);

    RequestPasswordResetCommand toRequestPasswordResetCommand(ForgotPasswordRequest request);

    ResetPasswordCommand toResetPasswordCommand(ResetPasswordRequest resetPasswordRequest);

}
