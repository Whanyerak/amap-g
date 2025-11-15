package fr.vat.amapg.amapg.userprofile.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

// TODO Annotation Factory
public interface UserProfileMongoDao extends MongoRepository<UserProfileMongoDto, UUID> {
}
