package org.com.ehotel.repository.user;

import org.com.ehotel.entity.user.AppUserEntity;
import org.com.ehotel.entity.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/14/2023, Tuesday
 **/
@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, String> {

    @Query(value = "SELECT * FROM appdb.ehotel.customer a WHERE a.customer_email = :customer_email", nativeQuery = true)
    Optional<CustomerEntity> findCustomerEntityByEmail(@Param("customer_email") String email);

    @Query("SELECT c FROM CustomerEntity c WHERE c.appUser = ?1")
    Optional<CustomerEntity> findCustomerEntityByAppUser(AppUserEntity appUser);

    @Query(value = "SELECT * FROM appdb.ehotel.customer a WHERE a.customer_nas = :customer_nas", nativeQuery = true)
    Optional<CustomerEntity> findCustomerEntityByNAS(@Param("customer_nas") String nas);

    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM appdb.ehotel.customer a WHERE a.customer_email = :customer_email", nativeQuery = true)
    boolean existsByEmail(@Param("customer_email") String email);

    @Modifying @Query(value = "DELETE FROM appdb.ehotel.customer a WHERE a.customer_email = :customer_email", nativeQuery = true)
    void deleteByEmail(@Param("customer_email") String email);

}