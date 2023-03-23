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

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record HotelDTO(
        Integer id, String name, String address, String email, String phoneNumber, Short rating,
        Integer chainHotelId, Set<RoleDTO> roles, Set<DepartmentDTO> departments,
        Set<RoomViewDTO> roomViews, Set<RoomTypeDTO> roomTypes, Set<RoomDTO> rooms,
        Set<ReviewDTO> reviews) {
    public HotelDTO(Integer id, String name, String address, String email, String phoneNumber,
                    Short rating, Integer chainHotelId) {
        this(id, name, address, email, phoneNumber, rating, chainHotelId,null,
                null, null, null, null, null);
    }
}