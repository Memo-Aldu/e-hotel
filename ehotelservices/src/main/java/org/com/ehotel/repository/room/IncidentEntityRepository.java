package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.IncidentEntity;
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
public interface IncidentEntityRepository extends JpaRepository<IncidentEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.incident WHERE incident_id = :id", nativeQuery = true)
    Optional<IncidentEntity> findIncidentById(@Param("id") Integer id);
    @Query(value = "SELECT * FROM appdb.ehotel.incident WHERE room_id = :room_id", nativeQuery = true)
    Set<IncidentEntity> findIncidentEntitiesByRoomId(@Param("room_id") Integer roomId);
    @Transactional @Modifying
    @Query(value = "DELETE FROM appdb.ehotel.incident WHERE room_id = :room_id", nativeQuery = true)
    void deleteAllByRoomId(@Param("room_id") Integer roomId);
}