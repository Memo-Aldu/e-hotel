package org.com.ehotel.mapper.user;

import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.entity.user.AppUserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
@Mapper @Component
public interface AppUserMapper {
    AppUserDTO toDTO(AppUserEntity entity);
    AppUserEntity toEntity(AppUserDTO dto);
    Set<AppUserDTO> toDTOs(Set<AppUserEntity> entities);
    Set<AppUserEntity> toEntities(Set<AppUserDTO> dtos);
}
