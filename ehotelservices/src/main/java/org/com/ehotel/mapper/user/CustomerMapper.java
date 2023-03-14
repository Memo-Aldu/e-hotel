package org.com.ehotel.mapper.user;

import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.user.CustomerEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Mapper @Component
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity entity);
    CustomerEntity toEntity(CustomerDTO dto);
    Set<CustomerDTO> toDTOs(Set<CustomerEntity> entities);
    Set<CustomerEntity> toEntities(Set<CustomerDTO> dtos);

}
