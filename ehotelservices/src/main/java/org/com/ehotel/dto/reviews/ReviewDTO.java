package org.com.ehotel.dto.reviews;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.reviews.ReviewPK;
import org.com.ehotel.entity.user.CustomerEntity;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ReviewDTO(
        ReviewPK id, Integer hotelId, String customerNAS,
        Short rating, String comment, Date date) {
}
