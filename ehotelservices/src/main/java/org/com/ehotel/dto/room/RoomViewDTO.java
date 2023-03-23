package org.com.ehotel.dto.room;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record RoomViewDTO(
        Integer id, Integer hotelId, Set<RoomTypeDTO> roomTypes,
        String name, String description) {
    public RoomViewDTO(Integer id, Integer hotelId, String name, String description) {
        this(id, hotelId, null, name, description);
    }
}
