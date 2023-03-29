package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface CommodityEntityRepository extends JpaRepository<CommodityEntity, Integer> {
    @Query("SELECT c FROM CommodityEntity c WHERE c.room.id = ?1")
    Set<CommodityEntity> findAllByRoomId(Integer id);
    @Query("SELECT c FROM CommodityEntity c WHERE c.id = ?1")
    Optional<CommodityEntity> findCommodityEntityById(Integer id);
    @Transactional @Modifying
    @Query("DELETE FROM CommodityEntity c WHERE c.room.id = ?1")
    void deleteAllByRoomId(Integer id);
}