package org.com.ehotel.repository.user;

import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.entity.user.EmployeeEntity;
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
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String> {
    // JPQL(Java Persistence Query Language) Query, targets the entity
    @Query("SELECT e FROM EmployeeEntity e WHERE e.NAS = ?1")
    Optional<EmployeeEntity> findEmployeeByNAS(String nas);
    @Query("SELECT e FROM EmployeeEntity e WHERE e.appUser = ?1")
    Optional<EmployeeEntity> findByAppUser(AppUserEntity appUser);
    // native query
    @Query(value = "SELECT * FROM appdb.ehotel.employee e WHERE e.employee_email = :employee_email", nativeQuery = true)
    Optional<EmployeeEntity> findEmployeeByEmail(@Param("employee_email") String email);
    @Query(value = "SELECT * FROM appdb.ehotel.employee e WHERE e.employee_dept = :employee_dept", nativeQuery = true)
    Set<EmployeeEntity> findEmployeesByDepartmentId(@Param("employee_dept") int branchId);
    @Query(value = "SELECT * FROM appdb.ehotel.employee e WHERE e.employee_role = :employee_role", nativeQuery = true)
    Set<EmployeeEntity> findEmployeesByRoleID(@Param("employee_role") int roleID);
    // native query (SQL Query) targets the table directly
    @Query(value = "SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM appdb.ehotel.employee e WHERE e.employee_nas = :employee_nas", nativeQuery = true)
    boolean existsByNAS(@Param("employee_nas") String nas);
    @Query(value = "SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM appdb.ehotel.employee e WHERE e.employee_email = :employee_email", nativeQuery = true)
    boolean existsByEmail(@Param("employee_email") String email);
    @Query(value = "SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM appdb.ehotel.employee e WHERE e.employee_phone_number = :phoneNumber", nativeQuery = true)
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    @Transactional @Modifying
    @Query("SELECT e FROM EmployeeEntity e WHERE e.NAS = ?1")
    void deleteEmployeeByNAS(String nas);
    @Transactional @Modifying
    @Query(value = "DELETE FROM appdb.ehotel.employee e WHERE e.employee_email = :employee_email", nativeQuery = true)
    void deleteEmployeeByEmail(@Param("employee_email") String email);

}