package org.com.ehotel.dto.room;

import org.com.ehotel.entity.room.RoomEntity;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record CommodityDTO(
        Integer id, Integer roomId, String name) {
}
