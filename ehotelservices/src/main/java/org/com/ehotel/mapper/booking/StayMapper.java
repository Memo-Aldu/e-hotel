package org.com.ehotel.mapper.booking;

import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.mapper.user.CustomerMapper;
import org.com.ehotel.mapper.user.EmployeeMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {EmployeeMapper.class, CustomerMapper.class})
public interface StayMapper {
    StayDTO toDTO(StayEntity entity);
    StayEntity toEntity(StayDTO dto);
    Set<StayDTO> toDTOs(Set<StayEntity> entities);
    Set<StayEntity> toEntities(Set<StayDTO> dtos);
}
