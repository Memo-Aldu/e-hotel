package org.com.ehotel.entity.user;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.booking.StayEntity;
import org.com.ehotel.entity.hotel.DepartmentEntity;
import org.com.ehotel.entity.hotel.RoleEntity;
import org.com.ehotel.entity.room.IncidentEntity;

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
@Table(name = "employee")
@Getter @Setter
public class EmployeeEntity {
    @Id
    @Column(name = "employee_nas", unique = true)
    private String NAS;

    @Column(name = "employee_phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "employee_name")
    private String firstName;

    @Column(name = "employee_middle_name")
    private String middleName;

    @Column(name = "employee_last_name")
    private String lastName;

    @Column(name = "employee_address")
    private String address;

    @Column(name = "employee_salary")
    private Double salary;

    @Column(name = "employee_dob")
    private Date dateOfBirth;

    @Column(name = "employee_registration_date")
    private Date registrationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "email", name = "employee_email", nullable = false)
    private AppUserEntity appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "role_id", name = "employee_role")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "dept_id", name = "employee_dept")
    private DepartmentEntity department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reporter", cascade = CascadeType.ALL)
    private Set<IncidentEntity> reports = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<StayEntity> confirmedStays = new HashSet<>();
}
