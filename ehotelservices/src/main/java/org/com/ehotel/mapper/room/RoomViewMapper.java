package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.RoomViewDTO;
import org.com.ehotel.entity.room.RoomViewEntity;
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
public interface RoomViewMapper {
    @Mapping(source = "hotel.id", target = "hotelId") @Mapping(target = "roomTypes", ignore = true)
    RoomViewDTO toDTO(RoomViewEntity entity);
    @Mapping(source = "hotelId", target = "hotel.id")
    RoomViewEntity toEntity(RoomViewDTO dto);
    Set<RoomViewDTO> toDTOs(Set<RoomViewEntity> entities);
    Set<RoomViewEntity> toEntities(Set<RoomViewDTO> dtos);
}
