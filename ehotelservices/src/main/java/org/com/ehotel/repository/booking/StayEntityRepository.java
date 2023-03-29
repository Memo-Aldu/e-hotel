package org.com.ehotel.repository.booking;

import org.com.ehotel.entity.booking.StayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface StayEntityRepository extends JpaRepository<StayEntity, Integer> {
}