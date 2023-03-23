package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.IncidentDTO;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.entity.room.IncidentEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.mapstruct.IterableMapping;
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
public interface RoomMapper {
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "stays", ignore = true) @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "extensions", ignore = true) @Mapping(target = "commodities", ignore = true)
    @Mapping(source = "type.id", target = "typeId") @Mapping(source = "hotel.id", target = "hotelId")
    RoomDTO toDTO(RoomEntity entity);
    @Mapping(source = "typeId", target = "type.id")
    @Mapping(source = "hotelId", target = "hotel.id")
    RoomEntity toEntity(RoomDTO dto);
    Set<RoomDTO> toDTOs(Set<RoomEntity> entities);
    Set<RoomEntity> toEntities(Set<RoomDTO> dtos);
}
