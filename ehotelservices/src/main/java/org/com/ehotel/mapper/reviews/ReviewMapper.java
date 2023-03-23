package org.com.ehotel.mapper.reviews;

import org.com.ehotel.dto.reviews.ReviewDTO;
import org.com.ehotel.entity.reviews.ReviewEntity;
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
public interface ReviewMapper {
    @Mapping(source = "hotel.id", target = "hotelId")
    @Mapping(source = "customer.NAS", target = "customerNAS")
    ReviewDTO toDTO(ReviewEntity entity);
    @Mapping(source = "hotelId", target = "hotel.id")
    @Mapping(source = "customerNAS", target = "customer.NAS")
    ReviewEntity toEntity(ReviewDTO dto);
    Set<ReviewDTO> toDTOs(Set<ReviewEntity> entities);
    Set<ReviewEntity> toEntities(Set<ReviewDTO> dtos);
}
