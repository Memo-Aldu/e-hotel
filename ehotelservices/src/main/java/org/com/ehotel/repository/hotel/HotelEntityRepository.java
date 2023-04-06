package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    @Transactional @Modifying
    @Query(value ="DELETE FROM appdb.ehotel.hotel a WHERE a.hotel_id = :id", nativeQuery = true)
    void deleteHotelEntityById(@Param("id") Integer id);
    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel a WHERE a.hotel_email = :email", nativeQuery = true)
    boolean existsHotelEntityByEmail(@Param("email") String email);
    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel a WHERE a.hotel_phone_number = :phoneNumber", nativeQuery = true)
    boolean existsHotelEntityByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
