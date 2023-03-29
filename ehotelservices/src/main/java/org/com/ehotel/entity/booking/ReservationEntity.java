package org.com.ehotel.entity.booking;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.room.ExtensionEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.enums.ReservationStatus;

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
@Table(name = "reservation")
@Getter @Setter
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", unique = true)
    private Integer id;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "special_request")
    private String specialRequest;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "customer_nas", name = "customer_nas")
    private CustomerEntity customer;

    //many to many with room
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservations")
    private Set<RoomEntity> reservedRooms = new HashSet<>();

    //many to many with extension
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservations")
    private Set<ExtensionEntity> reservedExtensions = new HashSet<>();
}