package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.ChainHotelEntity;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.entity.room.CommodityEntity;
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


//    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain a WHERE a.chain_name = :chain_name", nativeQuery = true)
//    Optional<ChainHotelEntity> findChainHotelEntityByName(@Param("chain_name") String chain_name);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain a WHERE a.chain_name = :chainName", nativeQuery = true)
    Optional<ChainHotelEntity> findChainHotelEntityByName(String chainName);

    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain a WHERE a.chain_rating = :rating", nativeQuery = true)
    Set<ChainHotelEntity> findAllByChainHotelEntityByRating(short rating);
    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain c WHERE c.chain_id = :id", nativeQuery = true)
    Optional<ChainHotelEntity> findChainHotelEntityById(Integer id);
    @Transactional
    @Modifying
    @Query(value =  "DELETE FROM appdb.ehotel.hotel_chain a WHERE a.chain_id = :id" , nativeQuery = true)
    void deleteChainHotelEntityByID(Integer id);

/**    @Transactional
    @Modifying
    @Query(value ="INSERT INTO appdb.ehotel.hotel_chain (chain_ID ,chain_email, chain_name , chain_phone_number, chain_rating, chain_central_address) VALUES (:chain_ID ,:chain_email, :chain_name , :chain_phone_number, :chain_rating, :chain_central_address)",nativeQuery = true )
    void createChainHotelEntity(@Param("chain_ID") Integer chain_ID, @Param("chain_email") String chain_email, @Param("chain_name") String chain_name, @Param("chain_phone_number") String chain_phone_number, @Param("chain_rating") short chain_rating, @Param(":chain_central_address") String chain_central_address);


/*    @Query(value = "SELECT * FROM appdb.ehotel.hotel_chain a WHERE a.chain_rating = :chain_rating", nativeQuery = true)
    Optional<ChainHotelEntity> findChainHotelEntityByRating(@Param("chain_rating") short chain_rating);


    @Modifying
    @Query(value = "UPDATE appdb.ehotel.hotel r SET r.chain_rating = :rating WHERE r.chain_ID= :chain_ID", nativeQuery = true)
    void updateChainHotelRating(@Param("chain_rating") String chain_rating, @Param("chain_ID") int chain_ID);


}
