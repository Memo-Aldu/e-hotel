package org.com.ehotel.dto.hotel;

import org.com.ehotel.entity.hotel.HotelEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ChainHotelDTO(
        Integer id, String name, String email, String phoneNumber,
        Short rating, String address, Set<HotelDTO> hotels) {
    public ChainHotelDTO(Integer id, String name, String email, String phoneNumber,
                         Short rating, String address) {
        this(id, name, email, phoneNumber, rating, address, null);
    }
}
