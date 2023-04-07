package org.com.ehotel.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.reviews.ReviewEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "customer")
@Getter @Setter
public class CustomerEntity {

    @Id
    @Column(name = "customer_nas", unique = true)
    private String NAS;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "email", name = "customer_email", nullable = false)
    private AppUserEntity appUser;

    @Column(name = "customer_phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "customer_name")
    private String firstName;

    @Column(name = "customer_middle_name")
    private String middleName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_dob")
    private Date dateOfBirth;

    @Column(name = "customer_registration_date")
    private Date registrationDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<StayEntity> stays = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ReservationEntity> reservations = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ReviewEntity> reviews = new HashSet<>();

}
