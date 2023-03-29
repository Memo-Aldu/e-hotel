package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HotelEntityRepository extends JpaRepository<HotelEntity, Integer> {


    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_name = :hotel_name", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByName(@Param("hotel_name") String chain_name);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_address = :hotel_address", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByAddress(@Param("hotel_address") String hotel_address);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_email = :hotel_email", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByEmail(@Param("hotel_email") String hotel_email);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_phone_number = :hotel_phone_number", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByPhoneNumber(@Param("hotel_phone_number") String hotel_phone_number);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_rating = :hotel_rating", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByRating(@Param("hotel_rating") short hotel_rating);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.chain_id in ( SELECT chain_id FROM appdb.ehotel.hotel_chain c WHERE c.chain_name = :chain_name)", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByChainHotelName(@Param("chain_name") String chain_name);

    // Update Hotel
    @Modifying
    @Query(value = "UPDATE appdb.ehotel.hotel r SET hotel_rating = :rating WHERE r.hotel_ID= :hotel_ID", nativeQuery = true)
    Optional<HotelEntity> updateHotelRating(@Param("rating") String rating, @Param("hotel_ID") int hotel_ID);

    @Modifying
    @Query(value = "UPDATE appdb.ehotel.hotel r SET hotel_name = :hotel_name WHERE r.hotel_ID= :hotel_ID", nativeQuery = true)
    Optional<HotelEntity> updateHotelName(@Param("hotel_name") String hotel_name, @Param("hotel_ID") int hotel_ID);

    @Modifying
    @Query(value = "UPDATE appdb.ehotel.hotel r SET hotel_address = :hotel_address WHERE r.hotel_ID= :hotel_ID", nativeQuery = true)
    Optional<HotelEntity> updateHotelAddress(@Param("hotel_address") String hotel_address, @Param("hotel_ID") int hotel_ID);

    // Delete Hotel

    @Modifying
    @Query(value = "DELETE FROM appdb.ehotel.hotel a WHERE a.hotel_name = :hotel_name", nativeQuery = true)
    void deleteHotelEntityByName(@Param("hotel_name") String hotel_name);

    /** Insert Hotel
     * */

    @Modifying
    @Query(value = "INSERT INTO appdb.ehotel.hotel (hotel_ID, chain_ID , hotel_name , hotel_address, hotel_email, hotel_phone_number, hotel_rating) VALUES (:hotel_ID, :chain_ID , :hotel_name , :hotel_address, :hotel_email, :hotel_phone_number, :hotel_rating)", nativeQuery = true)
    void insertHotelEntity(@Param("hotel_ID") int hotel_ID, @Param("chain_ID") int chain_ID, @Param("hotel_name") String hotel_name, @Param("hotel_address") String hotel_address, @Param("hotel_email") String hotel_email, @Param("hotel_phone_number") String hotel_phone_number,@Param("hotel_rating") int rating);


}
