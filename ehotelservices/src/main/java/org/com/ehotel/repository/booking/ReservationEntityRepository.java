package org.com.ehotel.repository.booking;

import org.com.ehotel.entity.booking.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.reservation r WHERE r.customer_nas = :id", nativeQuery = true)
    Set<ReservationEntity> findReservationsByCustomerNAS(@Param("id") String customerNAS);
    @Query(value = "SELECT RS.* FROM appdb.ehotel.reservation RS " +
            "INNER JOIN appdb.ehotel.room_reservation RR ON RR.reservation_id = RS.reservation_id " +
            "INNER JOIN appdb.ehotel.room R ON R.room_id = RR.room_id WHERE R.hotel_id = :hotelId ORDER BY RS.creation_date DESC", nativeQuery = true)
    Set<ReservationEntity> findReservationByHotelId(@Param("hotelId") Integer hotelId);
    @Query(value = "SELECT * FROM appdb.ehotel.reservation r WHERE r.reservation_id = :id", nativeQuery = true)
    Optional<ReservationEntity> findReservationById(@Param("id") Integer id);
    @Query(value = "DELETE FROM appdb.ehotel.reservation WHERE reservation_id = :id", nativeQuery = true)
    void deleteReservationByReservationId(@Param("id") Integer id);
    @Modifying @Transactional
    @Query(value = "INSERT INTO appdb.ehotel.room_reservation (reservation_id, room_id) VALUES (:id, :roomId)", nativeQuery = true)
    void insertReservation(@Param("id") Integer id, @Param("roomId") Integer roomId);


}