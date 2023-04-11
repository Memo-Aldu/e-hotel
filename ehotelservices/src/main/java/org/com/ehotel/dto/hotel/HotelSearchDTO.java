package org.com.ehotel.dto.hotel;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 4/10/2023, Monday
 **/
public record HotelSearchDTO(
        String query, Date checkIn, Date checkOut,
        Integer adults, Integer children, Double minPrice, Double maxPrice
) {
}
