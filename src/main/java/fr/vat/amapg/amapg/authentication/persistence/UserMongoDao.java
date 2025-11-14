package fr.vat.amapg.amapg.authentication.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserMongoDao extends MongoRepository<UserMongoDto, UUID> {

    Optional<UserMongoDto> findByUsername(String username);

}
