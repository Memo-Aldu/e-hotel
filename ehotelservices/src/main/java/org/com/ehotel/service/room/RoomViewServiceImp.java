package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.RoomViewDTO;
import org.com.ehotel.entity.room.RoomViewEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.room.RoomViewMapper;
import org.com.ehotel.repository.room.RoomViewEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/29/2023, Wednesday
 **/
@AllArgsConstructor @Service @Slf4j
public class RoomViewServiceImp implements RoomViewService {
    private final RoomViewEntityRepository roomViewRepository;
    private final RoomViewMapper roomViewMapper;

    @Override
    public Set<RoomViewDTO> getRoomViewsByHotelId(Integer hotelId) {
        log.info("Getting all room views by hotel id: " + hotelId);
        return roomViewMapper.toDTOs(
                roomViewRepository.findRoomViewByHotelId(hotelId));
    }

    @Override
    public RoomViewDTO getRoomViewById(Integer viewId) {
        return roomViewMapper.toDTO(
                roomViewRepository.findRoomViewById(viewId)
                        .orElseThrow(()-> new AppEntityNotFoundException("Room view not found with id: " + viewId))
        );
    }

    @Override
    public void deleteRoomViewById(Integer viewId) {
        if(!roomViewRepository.existsById(viewId)) {
            throw new AppEntityNotFoundException("Room view not found with id: " + viewId);
        }
        log.info("Deleting room view by id: " + viewId);
        roomViewRepository.deleteById(viewId);
    }

    @Override
    public RoomViewDTO createRoomView(RoomViewDTO roomViewDTO) {
        if(roomViewDTO.hotel().id() == null) {
            throw new BadRequestException("Hotel is required");
        }
        if(roomViewDTO.name() == null || roomViewDTO.name().isEmpty()) {
            throw new BadRequestException("Invalid name");
        }
        log.info("Creating room view: " + roomViewDTO.name());
        return roomViewMapper.toDTO(
                roomViewRepository.save(
                        roomViewMapper.toEntity(roomViewDTO)
                )
        );
    }

    @Override
    public RoomViewDTO updateRoomView(RoomViewDTO roomViewDTO, Integer viewId) {
        RoomViewEntity roomViewEntity = roomViewRepository.findRoomViewById(viewId)
                .orElseThrow(()-> new AppEntityNotFoundException("Room view not found with id: " + viewId));
        RoomViewEntity roomViewEntityUpdated = roomViewMapper.toEntity(roomViewDTO);
        if(roomViewEntityUpdated.getHotel().getId() != null) {
            roomViewEntity.setHotel(roomViewEntityUpdated.getHotel());
        }
        if(roomViewEntityUpdated.getName() != null && !roomViewEntityUpdated.getName().isEmpty()) {
            roomViewEntity.setName(roomViewEntityUpdated.getName());
        }
        if(roomViewEntityUpdated.getDescription() != null && !roomViewEntityUpdated.getDescription().isEmpty()) {
            roomViewEntity.setDescription(roomViewEntityUpdated.getDescription());
        }
        log.info("Updating room view by id: " + viewId);
        return roomViewMapper.toDTO(
                roomViewRepository.save(roomViewEntity)
        );
    }
}
