package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.RoleEntity;
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
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.role r WHERE r.role_id = :id", nativeQuery = true)
    Optional<RoleEntity> findRoleById(@Param("id") Integer id);
    @Query(value = "SELECT * FROM appdb.ehotel.role r WHERE r.hotel_id = :hotel_id", nativeQuery = true)
    Set<RoleEntity> findRoleByHotelId(@Param("hotel_id") Integer hotelId);
}