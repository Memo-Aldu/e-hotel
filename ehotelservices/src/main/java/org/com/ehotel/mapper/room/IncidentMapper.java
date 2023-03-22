package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.IncidentDTO;
import org.com.ehotel.entity.room.IncidentEntity;
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
public interface IncidentMapper {
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "reporter.NAS", target = "reporterId")
    IncidentDTO toDTO(IncidentEntity entity);
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "reporterId", target = "reporter.NAS")
    IncidentEntity toEntity(IncidentDTO dto);
    Set<IncidentDTO> toDTOs(Set<IncidentEntity> entities);
    Set<IncidentEntity> toEntities(Set<IncidentDTO> dtos);
}
