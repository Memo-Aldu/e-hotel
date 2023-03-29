package org.com.ehotel.dto.booking;


import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.enums.PaymentStatus;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record StayDTO(
        Integer id, Double paymentTotal, PaymentStatus paymentStatus,
        Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer,
        EmployeeDTO employee) {
}
