package org.com.ehotel.entity.hotel;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.user.EmployeeEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "role")
@Getter @Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true)
    private Integer id;

    @Column(name = "role_title")
    private String title;

    @Column(name = "role_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "hotel_id", name = "hotel_id")
    private HotelEntity hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private Set<EmployeeEntity> employees = new HashSet<>();

}
