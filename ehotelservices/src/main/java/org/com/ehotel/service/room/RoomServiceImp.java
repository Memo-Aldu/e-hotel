package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.dto.room.RoomSearchRequest;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.enums.RoomStatus;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.mapper.room.RoomMapper;
import org.com.ehotel.repository.room.RoomEntityRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/23/2023, Thursday
 **/
@Service @Slf4j
@AllArgsConstructor
public class RoomServiceImp implements RoomService{
    private final RoomEntityRepository roomRepository;
    private final RoomMapper roomMapper;
    @Override
    public Set<RoomDTO> searchRooms(RoomSearchRequest roomSearchRequest) {
        return roomMapper.toDTOs(searchRoomsByDate(roomSearchRequest));
    }

    @Override
    public RoomDTO saveRoom(RoomDTO roomDTO) {
        RoomEntity roomEntity = roomMapper.toEntity(roomDTO);
        roomEntity.setStatus(RoomStatus.UNOCCUPIED);
        return roomMapper.toDTO(roomRepository.save(roomEntity));
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO, Integer id) {
        RoomEntity roomEntity = roomRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Room not found with id: " + id));
        roomEntity.setRoomNumber(roomDTO.roomNumber());
        roomEntity.getType().setId(roomDTO.type().id());
        roomEntity.getHotel().setId(roomDTO.hotel().id());
        log.info("Room status: " + roomDTO.status());
        if(roomDTO.status() == null) {
            roomEntity.setStatus(RoomStatus.UNOCCUPIED);
        } else {
            roomEntity.setStatus(roomDTO.status());
        }
        return roomMapper.toDTO(roomRepository.save(roomEntity));

    }

    @Override
    public RoomDTO getRoomById(Integer id) {
        return roomMapper.toDTO(roomRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Room not found with id: " + id)));
    }

    @Override
    public void deleteRoom(Integer id) {
        if(!roomRepository.existsById(id)) {
            throw new AppEntityNotFoundException("Room not found with id: " + id);
        }
        log.info("Deleting room with id: " + id);
        roomRepository.deleteById(id);
    }

    private Set<RoomEntity> searchRoomsByDate(RoomSearchRequest roomSearchRequest) {
        Date checkInDate = Date.valueOf(roomSearchRequest.checkInDate());
        Date checkOutDate = Date.valueOf(roomSearchRequest.checkOutDate());
        String searchAddress = roomSearchRequest.searchAddress() == null ? "" : roomSearchRequest.searchAddress();
        short minRating = (roomSearchRequest.minRating()) == 0 ? 1 : roomSearchRequest.minRating();
        short maxRating = (roomSearchRequest.maxRating()) == 0 ? 5 : roomSearchRequest.maxRating();
        String hotelChainName = roomSearchRequest.hotelChainName() == null ? "" : roomSearchRequest.hotelChainName();
        double maxPricePerNight = roomSearchRequest.maxPricePerNight() == 0 ? 1000000 : roomSearchRequest.maxPricePerNight();
        int maxNumberOfGuests = roomSearchRequest.maxNumberOfGuests() == 0 ? 1 : roomSearchRequest.maxNumberOfGuests();
        return roomRepository.search( checkInDate, checkOutDate, searchAddress, minRating, maxRating,
                hotelChainName, maxPricePerNight, maxNumberOfGuests);
    }
}
