package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.RoomTypeDTO;
import org.com.ehotel.entity.room.RoomTypeEntity;
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
public interface RoomTypeMapper {
    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(source = "view.id", target = "viewId")
    RoomTypeDTO toDTO(RoomTypeEntity entity);
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "viewId", target = "view.id")
    RoomTypeEntity toEntity(RoomTypeDTO dto);
    Set<RoomTypeDTO> toDTOs(Set<RoomTypeEntity> entities);
    Set<RoomTypeEntity> toEntities(Set<RoomTypeDTO> dtos);
}
