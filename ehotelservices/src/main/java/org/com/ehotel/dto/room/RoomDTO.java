package org.com.ehotel.dto.room;

import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.enums.RoomStatus;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomDTO(
        Integer id, Integer roomNumber, RoomStatus status, Integer hotelId, Integer typeId,
        Set<IncidentDTO> incidents, Set<CommodityDTO> commodities, Set<ExtensionDTO> extensions,
        Set<ReservationDTO> reservations, Set<StayDTO> stays) {
    public RoomDTO(Integer id, Integer roomNumber, RoomStatus status, Integer hotelId, Integer typeId) {
        this(id, roomNumber, status, hotelId, typeId, null, null, null, null, null);
    }
}
