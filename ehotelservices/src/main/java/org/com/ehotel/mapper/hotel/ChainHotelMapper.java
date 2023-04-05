package org.com.ehotel.mapper.hotel;

import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring")
public interface ChainHotelMapper {
    ChainHotelDTO toDTO(ChainHotelEntity entity);
    ChainHotelEntity toEntity(ChainHotelDTO dto);
    Set<ChainHotelDTO> toDTOs(Set<ChainHotelEntity> entities);
    Set<ChainHotelEntity> toEntities(Set<ChainHotelDTO> dtos);
}
