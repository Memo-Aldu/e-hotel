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
@Component @Mapper
public interface DepartmentMapper {
    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(source = "manager.NAS", target = "managerId")
    DepartmentDTO toDTO(DepartmentEntity entity);
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "managerId", target = "manager.NAS")
    DepartmentEntity toEntity(DepartmentDTO dto);
    Set<DepartmentDTO> toDTOs(Set<DepartmentEntity> entities);
    Set<DepartmentEntity> toEntities(Set<DepartmentDTO> dtos);


}
