package org.com.ehotel.mapper.user;

import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper
public interface EmployeeMapper {
    @Mapping(source = "appUser.email", target = "email")
    @Mapping(source = "department.id", target = "departmentId") @Mapping(source = "role.id", target = "roleId")
    @Mapping(target = "confirmedStays", ignore = true) @Mapping(target = "reports", ignore = true)

    EmployeeDTO toDTO(EmployeeEntity entity);
    @Mapping(source = "email", target = "appUser.email")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleId", target = "role.id")
    EmployeeEntity toEntity(EmployeeDTO dto);
    Set<EmployeeDTO> toDTOs(Set<EmployeeEntity> entities);
    Set<EmployeeEntity> toEntities(Set<EmployeeDTO> dtos);
}
