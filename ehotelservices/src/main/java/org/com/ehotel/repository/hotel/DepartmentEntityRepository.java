package org.com.ehotel.repository.hotel;

import org.com.ehotel.entity.hotel.DepartmentEntity;
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
public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Integer> {
    @Query(value = "SELECT * FROM appdb.ehotel.department d WHERE d.dept_id = :dept_id", nativeQuery = true)
    Optional<DepartmentEntity> findDepartmentById(@Param("dept_id") Integer departmentId);
    @Query(value = "SELECT * FROM appdb.ehotel.department d WHERE d.dept_manager_nas = :manager_nas", nativeQuery = true)
    Set<DepartmentEntity> findDepartmentManagerNAS(@Param("manager_nas") String managerNAS);
    @Query(value = "SELECT * FROM appdb.ehotel.department d WHERE d.hotel_id = :hotel_id", nativeQuery = true)
    Set<DepartmentEntity> findDepartmentByHotelId(@Param("hotel_id") Integer hotelId);
}