package org.com.ehotel.repository.reviews;

import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.entity.reviews.ReviewPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, ReviewPK> {
}