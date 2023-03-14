package org.com.ehotel.dto.hotel;

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
        ChainHotelEntity chainHotel, Set<RoleEntity> roles, Set<DepartmentEntity> departments,
        Set<RoomViewEntity> roomViews, Set<RoomTypeEntity> roomTypes, Set<RoomEntity> rooms,
        Set<ReviewEntity> reviews) {
}
