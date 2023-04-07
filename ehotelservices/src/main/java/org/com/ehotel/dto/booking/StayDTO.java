package org.com.ehotel.dto.booking;


import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.enums.PaymentStatus;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record StayDTO(
        Integer id, Double paymentTotal, PaymentStatus paymentStatus,
        Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer,
        EmployeeDTO employee, List<Integer> rooms, List<Integer> requestedExtensions) {

    public StayDTO (Integer id, Double paymentTotal, PaymentStatus paymentStatus,
                    Date checkInDate, Date checkOutDate, Date creationDate, CustomerDTO customer,
                    EmployeeDTO employee, List<Integer> rooms, List<Integer> requestedExtensions) {
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.paymentTotal = paymentTotal;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.creationDate = creationDate;
        this.customer = customer;
        this.employee = employee;
        this.rooms = Objects.requireNonNullElseGet(rooms, List::of);
        this.requestedExtensions = Objects.requireNonNullElseGet(requestedExtensions, List::of);
    }
}
