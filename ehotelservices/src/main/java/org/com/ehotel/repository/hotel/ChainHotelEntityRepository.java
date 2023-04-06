package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.ChainHotelEntity;
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
public interface ChainHotelEntityRepository extends JpaRepository<ChainHotelEntity, Integer> {

    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain c WHERE c.chain_id = :id", nativeQuery = true)
    Optional<ChainHotelEntity> findChainHotelEntityById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain c", nativeQuery = true)
    Set<ChainHotelEntity> findAllByChainHotelEntity();
    @Transactional @Modifying
    @Query(value =  "DELETE FROM appdb.ehotel.hotel_chain a WHERE a.chain_id = :id" , nativeQuery = true)
    void deleteChainHotelEntityByID(@Param("id") Integer id);
    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel_chain c WHERE c.chain_email = :email", nativeQuery = true)
    boolean existsChainHotelEntityByEmail(@Param("email") String email);
    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM appdb.ehotel.hotel_chain c WHERE c.chain_phone_number = :phoneNumber", nativeQuery = true)
    boolean existsChainHotelEntityByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
