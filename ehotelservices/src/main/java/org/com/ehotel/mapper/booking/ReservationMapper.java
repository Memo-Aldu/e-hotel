package org.com.ehotel.mapper.booking;

import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.mapper.user.CustomerMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface ReservationMapper {
    ReservationDTO toDTO(ReservationEntity entity);
    ReservationEntity toEntity(ReservationDTO dto);
    Set<ReservationDTO> toDTOs(Set<ReservationEntity> entities);
    Set<ReservationEntity> toEntities(Set<ReservationDTO> dtos);
}
