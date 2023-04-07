package org.com.ehotel.entity.room;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.hotel.HotelEntity;
import org.com.ehotel.enums.RoomStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "room")
@Getter @Setter
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", unique = true)
    private Integer id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "occupancy_status")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "hotel_id", name = "hotel_id")
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "type_id", name = "room_type_id")
    private RoomTypeEntity type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    private Set<IncidentEntity> incidents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    private Set<CommodityEntity> commodities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
    private Set<ExtensionEntity> extensions = new HashSet<>();

    @ManyToMany(mappedBy = "reservedRooms")
    private Set<ReservationEntity> reservations = new HashSet<>();

    @ManyToMany(mappedBy = "rooms")
    private Set<StayEntity> stays = new HashSet<>();

}
