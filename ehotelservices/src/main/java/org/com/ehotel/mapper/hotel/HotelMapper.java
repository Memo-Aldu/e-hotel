package org.com.ehotel.mapper.hotel;

import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {ChainHotelMapper.class})
public interface HotelMapper {
    HotelDTO toDTO(HotelEntity entity);
    HotelEntity toEntity(HotelDTO dto);
    Set<HotelDTO> toDTOs(Set<HotelEntity> entities);
    Set<HotelEntity> toEntities(Set<HotelDTO> dtos);
}
