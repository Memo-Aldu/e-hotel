package org.com.ehotel.dto.hotel;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record DepartmentDTO(
        Integer id, String name, HotelDTO hotel, String managerNAS) {
}
