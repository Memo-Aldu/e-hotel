package org.com.ehotel.dto.room;

import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.room.RoomEntity;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record ExtensionDTO(
        Integer id, String name, Double price, RoomEntity room,
        Set<ReservationEntity> reservations, Set<StayEntity> stays) {
}
