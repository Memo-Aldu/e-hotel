package org.com.ehotel.repository.room;

import org.com.ehotel.entity.room.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface IncidentEntityRepository extends JpaRepository<IncidentEntity, Integer> {
}