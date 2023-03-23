package org.com.ehotel.mapper.hotel;

import org.com.ehotel.dto.hotel.RoleDTO;
import org.com.ehotel.entity.hotel.RoleEntity;
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
public interface RoleMapper {
    @Mapping(source = "hotel.id", target = "hotelId")
    RoleDTO toDTO(RoleEntity entity);
    @Mapping(source = "hotelId", target = "hotel.id")
    RoleEntity toEntity(RoleDTO dto);
    Set<RoleDTO> toDTOs(Set<RoleEntity> entities);
    Set<RoleEntity> toEntities(Set<RoleDTO> dtos);
}
