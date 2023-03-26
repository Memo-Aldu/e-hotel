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
            "AND C.chain_name LIKE CONCAT('%',:hotel_chain_name,'%') AND H.hotel_rating BETWEEN :hotel_rating_min AND :hotel_rating_max " +
            "AND H.hotel_address LIKE CONCAT('%',:hotel_address,'%') AND ((R.occupancy_status = 'UNOCCUPIED') OR " +
            "(R.room_id IN (SELECT S.room_id FROM appdb.ehotel.stay S WHERE ((S.check_out_date < :check_in_date) OR (:check_out_date < S.check_in_date))" +
            " ORDER BY S.check_out_date DESC)))" , nativeQuery = true)
    Set<RoomEntity> search(@Param("check_in_date") Date checkInDate , @Param("check_out_date") Date checkOutDate,
                           @Param("hotel_address") String hotelAddress, @Param("hotel_rating_min") short hotelRatingMin,
                           @Param("hotel_rating_max") short hotelRatingMax, @Param("hotel_chain_name") String hotelChainName,
                           @Param("price_per_night") double pricePerNight, @Param("capacity") int capacity);

    @Query(value = "SELECT * FROM appdb.ehotel.room WHERE room_id = :room_id", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomId(@Param("room_id") int room_id);


    /**
     * Search By Hotel info
     * SELECT * FROM room WHERE hotel_id in (select hotel_id from hotel where hotel_name = 'Enitan')
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE hotel_name = :hotel_name)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByHotelName(@Param("hotel_name") String hotel_name);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.hotel_address = :hotel_address)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByHotelAddress(@Param("hotel_address") String hotel_address);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.hotel_email =  :hotel_email)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByHotelEmail(@Param("hotel_email") String hotel_email);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.hotel_phone_number =  :hotel_phone_number)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByHotelPhoneNumber(@Param("hotel_phone_number") String hotel_phone_number);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE  r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.hotel_rating = :hotel_rating)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByHotelRating(@Param("hotel_rating") short hotel_rating);

    /**
     * Search By Hotel Chain info
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE  r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel a WHERE a.chain_ID in (SELECT chain_ID FROM appdb.ehotel.hotel_chain c WHERE c.chain_name = :chain_name))", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByChainHotelName(@Param("chain_name") String chain_name);

    /**
     * Search By View info
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.view_ID IN (SELECT view_ID FROM appdb.ehotel.room_view v WHERE v.view_description = :view_description))", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByViewDescription(@Param("view_description") String view_description);

    /**
     * Search By Room Type info
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.price_per_night = :price_per_night)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByPricePerNight(@Param("price_per_night") double price_per_night);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.capacity = :capacity)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByCapacity(@Param("capacity") String capacity);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.room_description = :room_description)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomDescription(@Param("room_description") String room_description);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.room_name = :room_name)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomName(@Param("room_name") String room_name);

    /**
     * Search By Multiple Attributes
     */
    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.price_per_night = :price_per_night AND t.capacity = :capacity)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomPriceAndRoomCapacity(@Param("price_per_night") String price_per_night, @Param("capacity") String capacity);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.price_per_night = :price_per_night AND t.room_description = :room_description)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomPriceAndRoomDescription(@Param("price_per_night") String price_per_night, @Param("room_description") String description);

    @Query(value = "SELECT * FROM appdb.ehotel.room a WHERE a.room_type_ID IN (SELECT type_ID FROM appdb.ehotel.room_type t WHERE t.price_per_night = :price_per_night AND t.capacity = :capacity) AND a.room_id IN (SELECT hotel_ID FROM appdb.ehotel.hotel h WHERE h.hotel_name = :hotel_name)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntityByRoomPriceAndRoomCapacityAndHotelName(@Param("price_per_night") double price_per_night, @Param("capacity") String capacity, @Param("hotel_name") String hotel_name);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.hotel_ID IN (SELECT hotel_ID FROM appdb.ehotel.hotel h WHERE h.hotel_name = :hotel_name) AND r.occupancy_status = :occupancy_status", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByHotelNameAndStatus(@Param("hotel_name") String hotel_name , @Param("occupancy_status") String occupancy_status);


    /** Search Room By Stay
     * */
    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.stay s WHERE s.check_in_date = :check_in_date)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByStayCheckInDate(@Param("check_in_date") String check_in_date);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.stay s WHERE s.check_out_date = :check_out_date)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByStayCheckOutDate(@Param("check_out_date") String check_out_date);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.stay s WHERE s.payment_status = :payment_status)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByStayPaymentStatus(@Param("payment_status") String payment_status);

    /** Search Room By Reservation
     * */
    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.reservation e WHERE e.check_in_date = :check_in_date)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByReservationCheckInDate(@Param("check_in_date") String check_in_date);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.reservation e WHERE e.check_out_date = :check_out_date)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByReservationCheckOutDate(@Param("check_out_date") String check_out_date);

    @Query(value = "SELECT * FROM appdb.ehotel.room r WHERE r.room_ID IN (SELECT room_ID FROM appdb.ehotel.reservation e WHERE e.reservation_status = :reservation_status)", nativeQuery = true)
    Optional<RoomEntity> findRoomEntitiesByReservationStatus(@Param("reservation_status") String reservation_status);


    /**
     * Update Room Status
     */

    @Modifying
    @Query(value = "UPDATE appdb.ehotel.room r SET occupancy_status = :occupancy_status WHERE r.room_ID= :room_ID", nativeQuery = true)
    Optional<RoomEntity> updateRoomStatus(@Param ("occupancy_status") String occupancy_status, @Param("room_ID") int room_ID);


    @Modifying
    @Query(value = "INSERT INTO appdb.ehotel.room (hotel_ID , room_type_ID, room_number) VALUES (:hotel_ID , :room_type_ID , :room_number)", nativeQuery = true)
    Integer insertRoomEntity(@Param("hotel_ID") int hotel_ID, @Param("room_type_ID") int room_type_ID, @Param("room_number") int room_number);

    @Modifying
    @Query(value = "UPDATE appdb.ehotel.room r SET occupancy_status = 'OCCUPIED' WHERE r.room_ID= :room_ID", nativeQuery = true)
    void deleteRoomEntityById( @Param("room_ID") int room_ID);

}
