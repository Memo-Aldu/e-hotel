package org.com.ehotel.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "app_user")
@Getter @Setter
public class AppUserEntity {
    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String userRole;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private EmployeeEntity employee;
}
