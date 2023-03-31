package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.RoomViewDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/29/2023, Wednesday
 **/
public interface RoomViewService {
    Set<RoomViewDTO> getRoomViewsByHotelId(Integer hotelId);
    RoomViewDTO getRoomViewById(Integer viewId);
    void deleteRoomViewById(Integer viewId);
    RoomViewDTO createRoomView(RoomViewDTO roomViewDTO);
    RoomViewDTO updateRoomView(RoomViewDTO roomViewDTO, Integer viewId);
}
