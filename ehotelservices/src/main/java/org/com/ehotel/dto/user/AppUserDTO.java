package org.com.ehotel.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.com.ehotel.enums.AppRoles;
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
@Builder
public record AppUserDTO(
        String email, String password, AppRoles userRole,
        CustomerDTO customer, EmployeeDTO employee, boolean isAccountNonExpired,
        boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnable,
        Collection<SimpleGrantedAuthority> authorities) implements Serializable, UserDetails {

    public AppUserDTO(String email, String password, AppRoles userRole, CustomerDTO customer, EmployeeDTO employee, boolean isEnable,
                      boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired) {
        this(email, password, userRole, customer, employee, true, true, true, isEnable,
                List.of(new SimpleGrantedAuthority(userRole.name())));
    }
    //Ignoring the value of authorities when returning the object as JSON
    @Override @JsonIgnore
    public Collection<SimpleGrantedAuthority> getAuthorities() {
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
        return this.isEnable;
    }
}
