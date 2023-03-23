package org.com.ehotel.dto.room;

import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.dto.booking.StayDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ExtensionDTO(
        Integer id, String name, Double price, Integer roomId,
        Set<ReservationDTO> reservations, Set<StayDTO> stays) {
    public ExtensionDTO(Integer id, String name, Double price, Integer roomId) {
        this(id, name, price, roomId, null, null);
    }
}
