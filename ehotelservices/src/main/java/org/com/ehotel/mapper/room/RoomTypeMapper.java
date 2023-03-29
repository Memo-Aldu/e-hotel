package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.RoomTypeDTO;
import org.com.ehotel.entity.room.RoomTypeEntity;
import org.com.ehotel.mapper.hotel.HotelMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {RoomViewMapper.class, HotelMapper.class})
public interface RoomTypeMapper {
    RoomTypeDTO toDTO(RoomTypeEntity entity);
    RoomTypeEntity toEntity(RoomTypeDTO dto);
    Set<RoomTypeDTO> toDTOs(Set<RoomTypeEntity> entities);
    Set<RoomTypeEntity> toEntities(Set<RoomTypeDTO> dtos);
}
