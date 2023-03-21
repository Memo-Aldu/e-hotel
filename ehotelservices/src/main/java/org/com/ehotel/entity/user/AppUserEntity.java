package org.com.ehotel.entity.user;

import lombok.*;
import org.com.ehotel.enums.AppRoles;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/

@Entity
@Table(name = "app_user")
@Getter @Setter @ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class AppUserEntity implements Serializable, UserDetails {
    @Id
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private AppRoles userRole;

    @Column(name = "account_non_expired")
    private Boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "account_enable")
    private Boolean isEnable;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private EmployeeEntity employee;

    // For Spring Security
    // Getters must be named as below
    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        if (this.userRole == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }
}
