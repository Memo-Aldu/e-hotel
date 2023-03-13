package org.com.ehotel.entity.hotel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "hotel_chain")
@Getter @Setter
public class ChainHotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chain_id", unique = true)
    private Integer id;

    @Column(name = "chain_name")
    private String name;

    @Column(name = "chain_email", unique = true)
    private String email;

    @Column(name = "chain_phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "chain_rating")
    private Short rating;

    @Column(name = "chain_central_address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chainHotel")
    private Set<HotelEntity> hotels = new HashSet<>();

}
