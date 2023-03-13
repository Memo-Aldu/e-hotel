package org.com.ehotel.entity.hotel;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.reviews.ReviewEntity;
import org.com.ehotel.entity.room.RoomEntity;
import org.com.ehotel.entity.room.RoomTypeEntity;
import org.com.ehotel.entity.room.RoomViewEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "hotel")
@Getter @Setter
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", unique = true)
    private Integer id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_address")
    private String address;

    @Column(name = "hotel_email", unique = true)
    private String email;

    @Column(name = "hotel_phone_number")
    private String phoneNumber;

    @Column(name = "hotel_rating")
    private Short rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "chain_id", name = "chain_id")
    private ChainHotelEntity chainHotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<DepartmentEntity> departments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<RoomViewEntity> roomViews = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<RoomTypeEntity> roomTypes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<RoomEntity> rooms = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<ReviewEntity> reviews = new HashSet<>();
}
