package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
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
public interface ExtensionEntityRepository extends JpaRepository<ExtensionEntity, Integer> {
    @Query("SELECT e FROM ExtensionEntity e WHERE e.room.id = ?1")
    Set<ExtensionEntity> getExtensionsByRoomId(Integer roomId);
    @Query(value = "SELECT * FROM appdb.ehotel.extension WHERE extension_id = :ext_id", nativeQuery = true)
    Optional<ExtensionEntity> getExtensionById(@Param("ext_id") Integer id);
    @Modifying @Transactional
    @Query(value = "DELETE FROM appdb.ehotel.extension WHERE room_id = :room_id", nativeQuery = true)
    void deleteAllByRoomId(@Param("room_id") Integer roomId);
}