package com.informeguaviare.mi_informe_guaviare.infrastructure.rest.mapper;

import com.informeguaviare.mi_informe_guaviare.application.command.CreateBossCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.CreateEmployeeCommand;
import com.informeguaviare.mi_informe_guaviare.application.command.CreateReportCommand;
import com.informeguaviare.mi_informe_guaviare.domain.model.Report;
import com.informeguaviare.mi_informe_guaviare.domain.model.User;
import com.informeguaviare.mi_informe_guaviare.infrastructure.persistence.mapper.ValueObjectMapper;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateBossRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateEmployeeRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.CreateReportRequest;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ProfileResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.ReportResponse;
import com.informeguaviare.mi_informe_guaviare.infrastructure.rest.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ValueObjectMapper.class})
public interface UserMapperDTO {

    CreateBossCommand toCreateBossCommand(CreateBossRequest request);

    CreateEmployeeCommand toCreateEmployeeCommand(CreateEmployeeRequest request);

    ProfileResponse toProfile(User user);

}
