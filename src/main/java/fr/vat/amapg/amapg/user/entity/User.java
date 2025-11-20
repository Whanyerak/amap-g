package fr.vat.amapg.amapg.user.entity;

import java.util.Set;

public record User(
        String username,
        String password,
        Set<UserRole> roles
) {
}
