package org.com.ehotel.dto.booking;

import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.user.CustomerEntity;
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
        Date checkInDate, Date checkOutDate, Date creationDate, String customerNAS,
        Set<RoomEntity> reservedRooms, Set<ExtensionDTO> reservedExtensions) {
public ReservationDTO(Integer id, ReservationStatus status, String specialRequest, Double totalPrice,
                          Date checkInDate, Date checkOutDate, Date creationDate, String customerNAS) {
    this(id, status, specialRequest, totalPrice, checkInDate, checkOutDate, creationDate,
            customerNAS, null, null);
}
}
