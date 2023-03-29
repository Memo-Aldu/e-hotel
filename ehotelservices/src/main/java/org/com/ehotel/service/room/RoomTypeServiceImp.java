package org.com.ehotel.service.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.RoomTypeDTO;
import org.com.ehotel.entity.room.RoomTypeEntity;
import org.com.ehotel.exceptions.AppEntityNotFoundException;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.mapper.room.RoomTypeMapper;
import org.com.ehotel.repository.room.RoomTypeEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/29/2023, Wednesday
 **/
@AllArgsConstructor @Service @Slf4j
public class RoomTypeServiceImp implements RoomTypeService{
    private final RoomTypeEntityRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public Set<RoomTypeDTO> getRoomTypesByHotelId(Integer hotelId) {
        log.info("Getting all room types by hotel id: " + hotelId);
        return roomTypeMapper.toDTOs(
                roomTypeRepository.findAllByHotelId(hotelId)
        );
    }

    @Override
    public RoomTypeDTO getRoomTypeById(Integer typeId) {
        log.info("Getting room type by id: " + typeId);
        return roomTypeMapper.toDTO(
                roomTypeRepository.findRoomTypeById(typeId).orElseThrow(
                        () -> new AppEntityNotFoundException("Room type not found")
                )
        );
    }

    @Override
    public void deleteRoomTypeById(Integer typeId) {
        if(!roomTypeRepository.existsById(typeId)) {
            throw new AppEntityNotFoundException("Room type not found");
        }
        log.info("Deleting room type by id: " + typeId);
        roomTypeRepository.deleteById(typeId);
    }

    @Override
    public RoomTypeDTO createRoomType(RoomTypeDTO roomTypeDTO) {
        if(roomTypeDTO.hotel().id() == null) {
            throw new BadRequestException("Hotel is required");
        }
        if(roomTypeDTO.pricePerNight() == null || roomTypeDTO.pricePerNight() < 0) {
            throw new BadRequestException("Invalid price per night");
        }
        if (roomTypeDTO.capacity() == null || roomTypeDTO.capacity() <= 0) {
            throw new BadRequestException("Invalid max capacity");
        }
        if (roomTypeDTO.roomName() == null || roomTypeDTO.roomName().isEmpty()) {
            throw new BadRequestException("Invalid name");
        }
        if (roomTypeDTO.view().id() == null) {
            throw new BadRequestException("Invalid view");
        }
        log.info("Creating room type: " + roomTypeDTO);
        return roomTypeMapper.toDTO(
                roomTypeRepository.save(
                        roomTypeMapper.toEntity(roomTypeDTO)
                )
        );
    }

    @Override
    public RoomTypeDTO updateRoomType(RoomTypeDTO roomTypeDTO, Integer id) {
        log.info("Updating room type: " + roomTypeDTO);
        RoomTypeEntity roomTypeEntity = roomTypeRepository
                .findRoomTypeById(id).orElseThrow(() -> new AppEntityNotFoundException("room type not found"));
        RoomTypeEntity updatedRoomType = roomTypeMapper.toEntity(roomTypeDTO);
        if(updatedRoomType.getHotel().getId() != null) {
            roomTypeEntity.setHotel(updatedRoomType.getHotel());
        }
        if(updatedRoomType.getPricePerNight() != null && updatedRoomType.getPricePerNight() >= 0) {
            roomTypeEntity.setPricePerNight(updatedRoomType.getPricePerNight());
        }
        if(updatedRoomType.getCapacity() != null && updatedRoomType.getCapacity() > 0) {
            roomTypeEntity.setCapacity(updatedRoomType.getCapacity());
        }
        if(updatedRoomType.getRoomName() != null && !updatedRoomType.getRoomName().isEmpty()) {
            roomTypeEntity.setRoomName(updatedRoomType.getRoomName());
        }
        if(updatedRoomType.getView().getId() != null) {
            roomTypeEntity.setView(updatedRoomType.getView());
        }
        if(updatedRoomType.getRoomDescription() != null && !updatedRoomType.getRoomDescription().isEmpty()) {
            roomTypeEntity.setRoomDescription(updatedRoomType.getRoomDescription());
        }
        return roomTypeMapper.toDTO(
                roomTypeRepository.save(roomTypeEntity)
        );
    }
}
