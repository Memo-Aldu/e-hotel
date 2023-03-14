package org.com.ehotel.mapper.room;

import org.com.ehotel.dto.room.CommodityDTO;
import org.com.ehotel.entity.room.CommodityEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper
public interface CommodityMapper {
    CommodityDTO toDTO(CommodityEntity entity);
    CommodityEntity toEntity(CommodityDTO dto);
    Set<CommodityDTO> toDTOs(Set<CommodityEntity> entities);
    Set<CommodityEntity> toEntities(Set<CommodityDTO> dtos);

}
