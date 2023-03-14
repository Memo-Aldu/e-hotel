package org.com.ehotel.dto.hotel;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.user.EmployeeEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record DepartmentDTO(
        Integer id, String name, HotelEntity hotel,
        Set<EmployeeEntity> employees, EmployeeEntity manager) {
}
