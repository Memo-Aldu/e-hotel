package org.com.ehotel.dto.user;

import org.com.ehotel.dto.hotel.DepartmentDTO;
import org.com.ehotel.dto.hotel.RoleDTO;

import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record EmployeeDTO(
        String NAS, String phoneNumber, String firstName, String middleName, String lastName,
        String address, Double salary, Date dateOfBirth, Date registrationDate, String email,
        RoleDTO role, DepartmentDTO department) {
}
