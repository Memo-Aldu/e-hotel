package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.RoomViewEntity;
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
public interface RoomViewEntityRepository extends JpaRepository<RoomViewEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.room_view WHERE view_id = ?1", nativeQuery = true)
    Optional<RoomViewEntity> findRoomViewById(Integer id);
    @Query(value = "SELECT * FROM appdb.ehotel.room_view WHERE hotel_id = ?1", nativeQuery = true)
    Set<RoomViewEntity> findRoomViewByHotelId(Integer hotelId);
}