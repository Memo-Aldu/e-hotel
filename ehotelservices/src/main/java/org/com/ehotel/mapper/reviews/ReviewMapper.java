package org.com.ehotel.mapper.reviews;

import org.com.ehotel.dto.reviews.ReviewDTO;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.mapper.hotel.HotelMapper;
import org.com.ehotel.mapper.user.CustomerMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {HotelMapper.class, CustomerMapper.class})
public interface ReviewMapper {
    ReviewDTO toDTO(ReviewEntity entity);
    ReviewEntity toEntity(ReviewDTO dto);
    Set<ReviewDTO> toDTOs(Set<ReviewEntity> entities);
    Set<ReviewEntity> toEntities(Set<ReviewDTO> dtos);
}
