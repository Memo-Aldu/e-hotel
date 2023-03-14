package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper
public interface ExtensionMapper {
    ExtensionDTO toDTO(ExtensionEntity entity);
    ExtensionEntity toEntity(ExtensionDTO dto);
    Set<ExtensionDTO> toDTOs(Set<ExtensionEntity> entities);
    Set<ExtensionEntity> toEntities(Set<ExtensionDTO> dtos);
}
