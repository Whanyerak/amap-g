package fr.vat.amapg.amapg.security.authentication.entity;

import fr.vat.amapg.amapg.security.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
public class AuthenticatedUser implements UserDetails {

    private final String username;
    private final String password;
    private final Set<UserRole> roles;

    public AuthenticatedUser(String username, String password, Set<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .toList();
    }

}
