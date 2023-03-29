package org.com.ehotel.dto.hotel;

import org.com.ehotel.dto.reviews.ReviewDTO;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.dto.room.RoomTypeDTO;
import org.com.ehotel.dto.room.RoomViewDTO;
import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.com.ehotel.entity.hotel.RoleEntity;
import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.room.RoomTypeEntity;
import org.com.ehotel.entity.room.RoomViewEntity;

import java.util.Set;

public record HotelDTO(
        Integer id, String name, String address, String email,
        String phoneNumber, Short rating, ChainHotelDTO chainHotel) {
    @JsonIgnore
    public boolean isValidDto() {
        return name != null || email != null || address != null || rating != null || phoneNumber != null;
    }
}
