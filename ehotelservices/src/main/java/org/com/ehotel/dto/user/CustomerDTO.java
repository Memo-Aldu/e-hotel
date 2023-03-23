package org.com.ehotel.dto.user;

import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.dto.reviews.ReviewDTO;

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
        Date registrationDate, Set<StayDTO> stays, Set<ReservationDTO> reservations,
        Set<ReviewDTO> reviews) {
    public CustomerDTO(String NAS, String email, String phoneNumber, String firstName,
                       String middleName, String lastName, String address, Date dateOfBirth,
                       Date registrationDate) {
        this(NAS, email, phoneNumber, firstName, middleName, lastName, address, dateOfBirth,
                registrationDate, null, null, null);
    }
}
