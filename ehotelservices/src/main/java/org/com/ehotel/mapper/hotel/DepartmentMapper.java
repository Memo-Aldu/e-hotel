package org.com.ehotel.mapper.hotel;

import org.com.ehotel.dto.hotel.DepartmentDTO;
import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {HotelMapper.class})
public interface DepartmentMapper {
    @Mapping(source = "manager.NAS", target = "managerNAS")
    DepartmentDTO toDTO(DepartmentEntity entity);
    @Mapping(source = "managerNAS", target = "manager.NAS")
    DepartmentEntity toEntity(DepartmentDTO dto);
    Set<DepartmentDTO> toDTOs(Set<DepartmentEntity> entities);
    Set<DepartmentEntity> toEntities(Set<DepartmentDTO> dtos);


}
