package org.com.ehotel.dto.booking;

import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.com.ehotel.enums.PaymentStatus;

import java.sql.Date;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record StayDTO(
        Integer id, Double paymentTotal, PaymentStatus paymentStatus,
        Date checkInDate, Date checkOutDate, Date creationDate, String customerNAS,
        String employeeNAS, Set<RoomDTO> rooms, Set<ExtensionDTO> requestedExtensions) {
    public StayDTO(Integer id, Double paymentTotal, PaymentStatus paymentStatus,
                   Date checkInDate, Date checkOutDate, Date creationDate, String customerNAS,
                   String employeeNAS) {
        this(id, paymentTotal, paymentStatus, checkInDate, checkOutDate,
        creationDate, customerNAS, employeeNAS, null, null);
    }
}
