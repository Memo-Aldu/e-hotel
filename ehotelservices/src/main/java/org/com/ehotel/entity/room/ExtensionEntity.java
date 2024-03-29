package org.com.ehotel.entity.room;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.booking.ReservationEntity;
import org.com.ehotel.entity.booking.StayEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "extension")
@Getter @Setter
public class ExtensionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "extension_name")
    private String name;

    @Column(name = "extension_price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "room_id", name = "room_id")
    private RoomEntity room;

    // many to many with reservation
    @ManyToMany(mappedBy = "reservedExtensions", fetch = FetchType.LAZY)
    private Set<ReservationEntity> reservations = new HashSet<>();

    // many to many with stay
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "requestedExtensions")
    private Set<StayEntity> stays = new HashSet<>();
}