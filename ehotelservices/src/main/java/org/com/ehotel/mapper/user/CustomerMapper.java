package org.com.ehotel.mapper.user;

import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.user.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Mapper @Component
public interface CustomerMapper {
    @Mapping(source = "appUser.email", target = "email")
    CustomerDTO toDTO(CustomerEntity entity);
    @Mapping(target = "appUser.email", source = "email")
    CustomerEntity toEntity(CustomerDTO dto);
    Set<CustomerDTO> toDTOs(Set<CustomerEntity> entities);
    Set<CustomerEntity> toEntities(Set<CustomerDTO> dtos);

}
