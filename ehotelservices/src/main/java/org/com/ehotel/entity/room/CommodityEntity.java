package org.com.ehotel.entity.room;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "commodity")
@Getter @Setter
public class CommodityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id", unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "room_id", name = "room_id")
    private RoomEntity room;

    @Column(name = "com_name")
    private String name;
}