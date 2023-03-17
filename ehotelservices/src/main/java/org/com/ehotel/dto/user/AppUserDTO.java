package org.com.ehotel.dto.user;

import org.com.ehotel.entity.user.CustomerEntity;
import org.com.ehotel.entity.user.EmployeeEntity;
import org.com.ehotel.enums.AppRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/13/2023, Monday
 **/
public record AppUserDTO(
     String email,
     String password,
     AppRoles userRole,
     CustomerEntity customer,
     EmployeeEntity employee,
     boolean isAccountNonExpired,
     boolean isAccountNonLocked,
     boolean isCredentialsNonExpired,
     boolean isEnable) implements Serializable, UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
