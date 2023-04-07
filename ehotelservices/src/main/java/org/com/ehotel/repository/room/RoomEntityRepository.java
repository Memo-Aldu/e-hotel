package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomEntityRepository extends JpaRepository<RoomEntity, Integer> {

    @Query(value = "SELECT R.* FROM appdb.ehotel.room R " +
            "INNER JOIN appdb.ehotel.room_type T ON R.room_type_id = T.type_id " +
            "INNER JOIN appdb.ehotel.room_view V ON T.view_id = V.view_id " +
            "INNER JOIN appdb.ehotel.hotel H ON R.hotel_id = H.hotel_id " +
            "INNER JOIN appdb.ehotel.hotel_chain C ON H.chain_id = C.chain_id WHERE T.price_per_night <= :price_per_night AND T.capacity = :capacity " +
            "AND LOWER(C.chain_name) LIKE CONCAT('%',:hotel_chain_name,'%') AND H.hotel_rating BETWEEN :hotel_rating_min AND :hotel_rating_max " +
            "AND LOWER(H.hotel_address) LIKE CONCAT('%',:hotel_address,'%') AND ((R.occupancy_status = 'UNOCCUPIED') OR " +
            "(R.room_id IN (SELECT RS.room_id FROM appdb.ehotel.stay S INNER JOIN appdb.ehotel.room_stay RS ON S.stay_id = RS.stay_id WHERE ((S.check_out_date < :check_in_date) OR (:check_out_date < S.check_in_date))" +
            " ORDER BY S.check_out_date DESC)))" , nativeQuery = true)
    Set<RoomEntity> search(@Param("check_in_date") Date checkInDate , @Param("check_out_date") Date checkOutDate,
                           @Param("hotel_address") String hotelAddress, @Param("hotel_rating_min") short hotelRatingMin,
                           @Param("hotel_rating_max") short hotelRatingMax, @Param("hotel_chain_name") String hotelChainName,
                           @Param("price_per_night") double pricePerNight, @Param("capacity") int capacity);

    @Query(value = "SELECT * FROM appdb.ehotel.room WHERE room_id = :room_id", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomId(@Param("room_id") int room_id);

    @Query(value = "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM appdb.ehotel.room r WHERE r.room_id = :room_id", nativeQuery = true)
    boolean existsByRoomId(@Param("room_id") Integer roomId);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_id = :hotel_id", nativeQuery = true)
    Set<RoomEntity> findRoomsByHotelId(@Param("hotel_id") Integer hotelId);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE  r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.hotel_rating = :hotel_rating)", nativeQuery = true)
    Set<RoomEntity> findRoomsByHotelRating(@Param("hotel_rating") short hotel_rating);

    /**
     * Search By Room Type info
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.price_per_night = :price_per_night)", nativeQuery = true)
    Set<RoomEntity> findRoomsByPricePerNight(@Param("price_per_night") double price_per_night);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.capacity = :capacity)", nativeQuery = true)
    Set<RoomEntity> findRoomsByCapacity(@Param("capacity") String capacity);

    /**
     * Update Room Status
     */
    @Modifying
    @Query(value = "UPDATE appdb.ehotel.room r SET occupancy_status = :occupancy_status WHERE r.room_ID= :room_ID", nativeQuery = true)
    Optional<RoomEntity> updateRoomStatus(@Param ("occupancy_status") String occupancy_status, @Param("room_ID") int room_ID);

    @Modifying
    @Query(value = "INSERT INTO appdb.ehotel.room (hotel_ID , room_type_ID, room_number) VALUES (:hotel_ID , :room_type_ID , :room_number)", nativeQuery = true)
    Integer insertRoomEntity(@Param("hotel_ID") int hotel_ID, @Param("room_type_ID") int room_type_ID, @Param("room_number") int room_number);
}
