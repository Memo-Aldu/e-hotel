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
@Table(name = "room_view")
@Getter @Setter
public class RoomViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id", unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "hotel_id", name = "hotel_id")
    private HotelEntity hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "view", cascade = CascadeType.ALL)
    private Set<RoomTypeEntity> roomTypes = new HashSet<>();

    @Column(name = "view_name")
    private String name;

    @Column(name = "view_description")
    private String description;
}
