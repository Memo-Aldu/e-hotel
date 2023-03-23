package org.com.ehotel.mapper.booking;

import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.entity.booking.ReservationEntity;
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
public interface ReservationMapper {
    @Mapping(source = "customer.NAS", target = "customerNAS")
    @Mapping(target = "reservedRooms", ignore = true) @Mapping(target = "reservedExtensions", ignore = true)
    ReservationDTO toDTO(ReservationEntity entity);
    @Mapping(source = "customerNAS", target = "customer.NAS")
    ReservationEntity toEntity(ReservationDTO dto);
    Set<ReservationDTO> toDTOs(Set<ReservationEntity> entities);
    Set<ReservationEntity> toEntities(Set<ReservationDTO> dtos);
}
