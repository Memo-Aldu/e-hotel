package org.com.ehotel.dto.room;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.room.RoomTypeEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record RoomViewDTO(
        Integer id, Integer hotelId, Set<RoomTypeEntity> roomTypes,
        String name, String description) {
}
