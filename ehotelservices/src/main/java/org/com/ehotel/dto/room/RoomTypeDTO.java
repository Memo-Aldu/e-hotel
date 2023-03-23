package org.com.ehotel.dto.room;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomTypeDTO(
        Integer id, Integer hotelId, Integer viewId,
        Set<RoomDTO> rooms, Double pricePerNight, Short capacity,
        String roomName, String roomDescription) {
    public RoomTypeDTO(Integer id, Integer hotelId, Integer viewId, Double pricePerNight,
                       Short capacity, String roomName, String roomDescription) {
        this(id, hotelId, viewId, null, pricePerNight, capacity, roomName, roomDescription);
    }
}
