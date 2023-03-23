package org.com.ehotel.mapper.booking;

import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.entity.booking.StayEntity;
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
public interface StayMapper {
    @Mapping(source = "customer.NAS", target = "customerNAS")
    @Mapping(source = "employee.NAS", target = "employeeNAS")
    @Mapping(target = "rooms", ignore = true) @Mapping(target = "requestedExtensions", ignore = true)
    StayDTO toDTO(StayEntity entity);
    @Mapping(source = "customerNAS", target = "customer.NAS")
    @Mapping(source = "employeeNAS", target = "employee.NAS")
    StayEntity toEntity(StayDTO dto);
    Set<StayDTO> toDTOs(Set<StayEntity> entities);
    Set<StayEntity> toEntities(Set<StayDTO> dtos);
}
