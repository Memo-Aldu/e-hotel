package org.com.ehotel.mapper.booking;

import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.mapper.user.CustomerMapper;
import org.com.ehotel.mapper.user.EmployeeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Component @Mapper(componentModel = "spring", uses = {EmployeeMapper.class, CustomerMapper.class})
public interface StayMapper {
    @Mapping(target = "rooms", source = "rooms", qualifiedByName = "mapEntities")
    @Mapping(target = "requestedExtensions", source = "requestedExtensions", qualifiedByName = "mapEntities")
    StayDTO toDTO(StayEntity entity);
    @Mapping(target = "rooms", source = "rooms", qualifiedByName = "mapRoomDTOs")
    @Mapping(target = "requestedExtensions", source = "requestedExtensions", qualifiedByName = "mapExtensionDTOs")
    StayEntity toEntity(StayDTO dto);
    Set<StayDTO> toDTOs(Set<StayEntity> entities);
    Set<StayEntity> toEntities(Set<StayDTO> dtos);

    @Named("mapEntities")
    default List<Integer> mapRoomEntities(Set<?> entities){
        return entities.stream().map(entity -> {
            if(entity instanceof RoomEntity){
                return ((RoomEntity) entity).getId();
            }else if(entity instanceof ExtensionEntity){
                return ((ExtensionEntity) entity).getId();
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Named("mapRoomDTOs")
    default Set<RoomEntity> mapRoomDTOs(List<Integer> rooms){
        return rooms.stream().map(room -> {
            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setId(room);
            return roomEntity;
        }).collect(Collectors.toSet());
    }

    @Named("mapExtensionDTOs")
    default Set<ExtensionEntity> mapExtensionDTOs(List<Integer> extensions){
        return extensions.stream().map(extension -> {
            ExtensionEntity extensionEntity = new ExtensionEntity();
            extensionEntity.setId(extension);
            return extensionEntity;
        }).collect(Collectors.toSet());
    }
}
