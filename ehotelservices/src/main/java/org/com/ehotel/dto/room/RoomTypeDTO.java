package org.com.ehotel.dto.room;

import org.com.ehotel.dto.hotel.HotelDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomTypeDTO(
        Integer id, HotelDTO hotel, RoomViewDTO view, Double pricePerNight, Short capacity,
        String roomName, String roomDescription) {
}
