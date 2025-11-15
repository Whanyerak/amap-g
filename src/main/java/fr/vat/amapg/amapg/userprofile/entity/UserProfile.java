package fr.vat.amapg.amapg.userprofile.entity;

public record UserProfile(
        String userId,
        String firstName,
        String lastName,
        String address,
        String postalCode,
        String city,
        String phoneNumber,
        String email
) {

    public String getIdentitySeed() {
        return firstName + lastName + email;
    }

}
