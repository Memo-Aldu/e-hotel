package org.com.ehotel.entity.room;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.hotel.HotelEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "room_type")
@Getter @Setter
public class RoomTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "hotel_id", name = "hotel_id")
    private HotelEntity hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "view_id", name = "view_id")
    private RoomViewEntity view;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL)
    private Set<RoomEntity> rooms = new HashSet<>();

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @Column(name = "capacity")
    private Short capacity;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_description")
    private String roomDescription;
}
