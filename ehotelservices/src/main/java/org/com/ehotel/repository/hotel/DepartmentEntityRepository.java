package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Integer> {
}