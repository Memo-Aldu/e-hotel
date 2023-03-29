package org.com.ehotel.repository.user;

import org.com.ehotel.entity.user.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface AppUserEntityRepository extends JpaRepository<AppUserEntity, String> {

    @Query(value = "SELECT * FROM appdb.ehotel.app_user a WHERE a.email = :employee_email", nativeQuery = true)
    Optional<AppUserEntity> findAppUserEntityByEmail(@Param("employee_email") String email);

    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.app_user a WHERE a.email = :employee_email", nativeQuery = true)
    boolean existsByEmail(@Param("employee_email") String email);

    @Transactional @Modifying
    @Query(value = "DELETE FROM appdb.ehotel.app_user a WHERE a.email = :employee_email", nativeQuery = true)
    void deleteByEmail(@Param("employee_email") String email);

    @Transactional @Modifying
    @Query(value = "INSERT INTO appdb.ehotel.app_user (email, password)  VALUES (:email, :password)", nativeQuery = true)
    void insertUserData(@Param("email") String email, @Param("password") String password);

    @Transactional @Modifying
    @Query(value = "INSERT INTO appdb.ehotel.app_user (email, password, user_role)  " +
            "VALUES (:email, :password, :role)", nativeQuery = true)
    void insertUserData(@Param("email") String email, @Param("password") String password, @Param("role") String role);
}