package fr.vat.amapg.amapg.security.register.entity;

public record User(
        String firstName,
        String lastName,
        String address,
        String postalCode,
        String city,
        String phoneNumber,
        String email
) {
}
