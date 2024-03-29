package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.IncidentDTO;
import org.com.ehotel.entity.room.IncidentEntity;
import org.com.ehotel.mapper.user.EmployeeMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {RoomMapper.class, EmployeeMapper.class})
public interface IncidentMapper {
    IncidentDTO toDTO(IncidentEntity entity);
    IncidentEntity toEntity(IncidentDTO dto);
    Set<IncidentDTO> toDTOs(Set<IncidentEntity> entities);
    Set<IncidentEntity> toEntities(Set<IncidentDTO> dtos);
}
