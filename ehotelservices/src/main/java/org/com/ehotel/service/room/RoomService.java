package org.com.ehotel.service.room;

import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.dto.room.RoomSearchRequest;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/23/2023, Thursday
**/
public interface RoomService {
    Set<RoomDTO> searchRooms(RoomSearchRequest roomSearchRequest);
    RoomDTO saveRoom(RoomDTO roomDTO);
    RoomDTO updateRoom(RoomDTO roomDTO, Integer id);
    RoomDTO getRoomById(Integer id);
    void deleteRoom(Integer id);
}
