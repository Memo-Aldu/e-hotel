package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelEntityRepository extends JpaRepository<HotelEntity, Integer> {

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a WHERE a.hotel_name = :name", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByName(String name);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.hotel_rating = :rating", nativeQuery = true)
    Set<HotelEntity> findAllHotelEntityByRating(short rating);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.hotel_id = :id", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityById(Integer id);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.hotel_address = :address", nativeQuery = true)
    Optional<HotelEntity> findHotelEntityByAddress(String address);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel a  WHERE a.chain_id IN (SELECT chain_id FROM appdb.ehotel.hotel_chain c WHERE c.chain_name =:name)", nativeQuery = true)
    Set<HotelEntity> findAllHotelEntityByChainName(String name);

    @Transactional
    @Modifying
    @Query(value ="DELETE FROM appdb.ehotel.hotel a WHERE a.hotel_id = :id", nativeQuery = true)
    void deleteHotelEntityById(Integer id);


}
