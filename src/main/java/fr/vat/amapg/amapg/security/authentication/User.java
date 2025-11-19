package fr.vat.amapg.amapg.security.authentication;

import fr.vat.amapg.amapg.security.UserRole;

import java.util.Set;

public record User(
        String username,
        String password,
        Set<UserRole> roles
) {
}
