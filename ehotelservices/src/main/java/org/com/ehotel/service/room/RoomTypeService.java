package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.RoomTypeDTO;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/29/2023, Wednesday
 **/
public interface RoomTypeService {
    Set<RoomTypeDTO> getRoomTypesByHotelId(Integer hotelId);
    RoomTypeDTO getRoomTypeById(Integer typeId);
    void deleteRoomTypeById(Integer typeId);
    RoomTypeDTO createRoomType(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO updateRoomType(RoomTypeDTO roomTypeDTO, Integer id);
}
