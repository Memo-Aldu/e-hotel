package org.com.ehotel.dto.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.enums.RoomStatus;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomDTO(
        Integer id, Integer roomNumber, RoomStatus status, HotelDTO hotel, RoomTypeDTO type) {
    @JsonIgnore
    public boolean isValid() {
        return id != null || roomNumber == null || hotel == null || type == null;
    }
}
