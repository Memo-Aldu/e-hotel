package org.com.ehotel.dto.booking;

import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.enums.ReservationStatus;

import java.sql.Date;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ReservationDTO(
        Integer id, ReservationStatus status, String specialRequest, Double totalPrice,
        Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer) {
}
