package org.com.ehotel.dto.room;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.room.RoomViewEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomTypeDTO(
        Integer id, HotelEntity hotel, RoomViewEntity view,
        Set<RoomEntity> rooms, Double pricePerNight, Short capacity,
        String roomName, String roomDescription) {
}
