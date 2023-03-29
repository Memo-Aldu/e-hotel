package org.com.ehotel.dto.reviews;

import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.entity.reviews.ReviewPK;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ReviewDTO(
        ReviewPK id, HotelDTO hotel, CustomerDTO customer,
        Short rating, String comment, Date date) {
}
