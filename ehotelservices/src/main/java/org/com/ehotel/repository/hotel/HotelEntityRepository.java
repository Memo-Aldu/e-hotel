package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelEntityRepository extends JpaRepository<HotelEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.hotel_id = :id", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityById(@Param("id") Integer id);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel c", nativeQuery = true)
    Set<HotelEntity> findAllHotelEntity();
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.chain_id = :chain_id", nativeQuery = true)
    Set<HotelEntity> findAllHotelEntityByChainId(@Param("chain_id") Integer chainId);
    @Query(value = "SELECT H.* FROM appdb.ehotel.hotel H " +
            "INNER JOIN appdb.ehotel.hotel_chain HC ON H.chain_id = HC.chain_id " +
            "INNER JOIN appdb.ehotel.room R ON H.hotel_id = R.hotel_id " +
            "INNER JOIN appdb.ehotel.room_type T ON R.room_type_id = T.type_id " +
            "WHERE  (LOWER(H.hotel_name) LIKE :query OR LOWER(H.city) LIKE :query " +
            "OR LOWER(H.hotel_address) LIKE :query OR LOWER(HC.chain_name) LIKE :query) " +
            "AND T.capacity >= :capacity AND ((R.occupancy_status = 'UNOCCUPIED') OR " +
            "(R.room_id IN (SELECT RS.room_id FROM appdb.ehotel.stay S INNER JOIN appdb.ehotel.room_stay RS ON S.stay_id = RS.stay_id " +
            "WHERE ((S.check_out_date < :checkIn) OR (:checkOut < S.check_in_date))))) ORDER BY H.hotel_rating DESC" , nativeQuery = true)
    Set<HotelEntity> searchHotel(@Param("query") String query, @Param("checkIn") Date checkIn, @Param("checkOut") Date checkOut, @Param("capacity") Integer capacity);
    @Transactional @Modifying
    @Query(value ="DELETE FROM appdb.ehotel.hotel a WHERE a.hotel_id = :id", nativeQuery = true)
    void deleteHotelEntityById(@Param("id") Integer id);
    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel a WHERE a.hotel_email = :email", nativeQuery = true)
    boolean existsHotelEntityByEmail(@Param("email") String email);
    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel a WHERE a.hotel_phone_number = :phoneNumber", nativeQuery = true)
    boolean existsHotelEntityByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
