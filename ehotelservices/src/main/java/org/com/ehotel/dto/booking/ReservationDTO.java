package org.com.ehotel.dto.booking;

import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.enums.ReservationStatus;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ReservationDTO(
        Integer id, ReservationStatus status, String specialRequest, Double totalPrice,
        Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer,
        List<Integer> reservedRooms, List<Integer> reservedExtensions) {

    public ReservationDTO (Integer id, ReservationStatus status, String specialRequest, Double totalPrice,
                           Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer,
                           List<Integer> reservedRooms, List<Integer> reservedExtensions) {
        this.id = id;
        this.status = status;
        this.specialRequest = specialRequest;
        this.totalPrice = totalPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.creationDate = creationDate;
        this.customer = customer;
        this.reservedRooms = Objects.requireNonNullElseGet(reservedRooms, List::of);
        this.reservedExtensions = Objects.requireNonNullElseGet(reservedExtensions, List::of);
    }
}
