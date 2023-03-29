package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface RoomTypeEntityRepository extends JpaRepository<RoomTypeEntity, Integer> {
    @Query("SELECT r FROM RoomTypeEntity r WHERE r.id = ?1")
    Optional<RoomTypeEntity> findRoomTypeById(Integer integer);
    @Query("SELECT r FROM RoomTypeEntity r WHERE r.hotel.id = ?1")
    Set<RoomTypeEntity> findAllByHotelId(Integer hotelId);
}