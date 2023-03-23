package org.com.ehotel.dto.hotel;

import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.user.EmployeeEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record DepartmentDTO(
        Integer id, String name, Integer hotelId,
        Set<EmployeeDTO> employees, Integer managerId) {
    public DepartmentDTO(Integer id, String name, Integer hotelId) {
        this(id, name, hotelId, null, null);
    }
}
