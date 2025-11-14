package fr.vat.amapg.amapg.security.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

// TODO Annotation Factory
public interface UserMongoDao extends MongoRepository<UserMongoDto, UUID> {

    Optional<UserMongoDto> findByUsername(String username);

}
