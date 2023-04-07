package org.com.ehotel.repository.booking;

import org.com.ehotel.entity.booking.StayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface StayEntityRepository extends JpaRepository<StayEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.stay s WHERE s.stay_id = :stay_id", nativeQuery = true)
    Optional<StayEntity> findStayById(@Param("stay_id") Integer stayId);
    @Query(value = "SELECT * FROM appdb.ehotel.stay s WHERE s.customer_nas = :customer_nas", nativeQuery = true)
    Set<StayEntity> findStayByCustomerNAS(@Param("customer_nas") String customerNAS);
    @Query(value = "SELECT S.* FROM appdb.ehotel.stay S " +
            "INNER JOIN appdb.ehotel.room_stay RS ON RS.stay_id = S.stay_id " +
            "INNER JOIN appdb.ehotel.room R ON R.room_id = RS.room_id WHERE R.hotel_id = :hotel_id ORDER BY S.creation_date DESC", nativeQuery = true)
    Set<StayEntity> findStayByHotelId(@Param("hotel_id") Integer hotelId);
    @Query(value = "DELETE FROM appdb.ehotel.stay WHERE stay_id = :stay_id", nativeQuery = true)
    void deleteStayByStayId(@Param("stay_id") Integer stayId);

}