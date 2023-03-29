package org.com.ehotel.dto.room;

import org.com.ehotel.dto.hotel.HotelDTO;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record RoomViewDTO(
        Integer id, HotelDTO hotel, String name, String description) {
}
