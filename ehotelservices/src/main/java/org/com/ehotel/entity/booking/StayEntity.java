package org.com.ehotel.entity.booking;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.com.ehotel.enums.PaymentStatus;

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
@Table(name = "stay")
@Getter @Setter
public class StayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stay_id", unique = true)
    private Integer id;

    @Column(name = "payment_total", nullable = false)
    private Double paymentTotal;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "customer_nas", name = "customer_nas")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "employee_nas", name = "employee_nas")
    private EmployeeEntity employee;

    //many to many with room
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "room_stay",
            joinColumns = @JoinColumn(name = "stay_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<RoomEntity> rooms = new HashSet<>();

    //many to many with extension
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "extension_stay",
            joinColumns = @JoinColumn(name = "stay_id"),
            inverseJoinColumns = @JoinColumn(name = "extension_id"))
    private Set<ExtensionEntity> requestedExtensions = new HashSet<>();

}