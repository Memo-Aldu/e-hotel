package org.com.ehotel.repository.reviews;

import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.entity.reviews.ReviewPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, ReviewPK> {
    @Query(value = "SELECT * FROM appdb.ehotel.reviews WHERE hotel_id = ?1 AND customer_nas = ?2", nativeQuery = true)
    Optional<ReviewEntity> findReviewEntitiesByIdHotelIdAndIdCustomerId(Integer hotelId, String customerId);
    @Query(value = "SELECT * FROM appdb.ehotel.reviews WHERE hotel_id = ?1", nativeQuery = true)
    Set<ReviewEntity> findReviewsByHotelId(Integer hotelId);
    @Query(value = "SELECT * FROM appdb.ehotel.reviews WHERE customer_nas = ?1", nativeQuery = true)
    Set<ReviewEntity> findReviewByCustomerNas(String customerId);
}