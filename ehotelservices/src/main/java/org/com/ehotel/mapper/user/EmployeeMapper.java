package org.com.ehotel.mapper.user;

import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.com.ehotel.mapper.hotel.DepartmentMapper;
import org.com.ehotel.mapper.hotel.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {RoleMapper.class, DepartmentMapper.class})
public interface EmployeeMapper {
    @Mapping(source = "appUser.email", target = "email")
    EmployeeDTO toDTO(EmployeeEntity entity);
    @Mapping(target = "appUser.email", source = "email")
    EmployeeEntity toEntity(EmployeeDTO dto);
    Set<EmployeeDTO> toDTOs(Set<EmployeeEntity> entities);
    Set<EmployeeEntity> toEntities(Set<EmployeeDTO> dtos);
}
