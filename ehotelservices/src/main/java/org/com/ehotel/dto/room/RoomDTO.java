package org.com.ehotel.dto.room;

import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.room.CommodityEntity;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.IncidentEntity;
import org.com.ehotel.entity.room.RoomTypeEntity;
import org.com.ehotel.enums.RoomStatus;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

public record RoomDTO(
        Integer id, Integer roomNumber, RoomStatus status, Integer hotelId, Integer typeId,
        Set<IncidentEntity> incidents, Set<CommodityEntity> commodities, Set<ExtensionEntity> extensions,
        Set<ReservationEntity> reservations, Set<StayEntity> stays) {
}
