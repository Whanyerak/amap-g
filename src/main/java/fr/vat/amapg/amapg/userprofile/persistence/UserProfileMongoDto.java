package fr.vat.amapg.amapg.userprofile.persistence;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "userProfile")
public record UserProfileMongoDto(
    @MongoId UUID id,
    UUID userId,
    String firstName,
    String lastName,
    String address,
    String postalCode,
    String city,
    String phoneNumber,
    String email
) {
}
