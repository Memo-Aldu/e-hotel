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
@Table(name = "department")
@Getter @Setter
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id", unique = true)
    private Integer id;

    @Column(name = "dept_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "hotel_id", name = "hotel_id")
    private HotelEntity hotel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL)
    private Set<EmployeeEntity> employees = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "employee_nas", name = "dept_manager_nas", nullable = false)
    private EmployeeEntity manager;


}
