package org.com.ehotel.dto.user;


import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record CustomerDTO(
        String NAS, String email, String phoneNumber, String firstName,
        String middleName, String lastName, String address, Date dateOfBirth,
        Date registrationDate) {
}
