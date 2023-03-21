package org.com.ehotel.dto.user;

import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.entity.user.AppUserEntity;

import java.sql.Date;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record CustomerDTO(
        String NAS, String email, String phoneNumber, String firstName,
        String middleName, String lastName, String address, Date dateOfBirth,
        Date registrationDate, Set<StayEntity> stays, Set<ReservationEntity> reservations,
        Set<ReviewEntity> reviews) {
}
