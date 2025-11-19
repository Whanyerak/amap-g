package fr.vat.amapg.amapg.user.persistence;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;
import java.util.UUID;

@Getter
@Document(collection = "user")
public class UserMongoDto {

    private final @MongoId UUID id;
    private final String username;
    private final String password;
    private final Set<String> roles;

    public UserMongoDto(UUID id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}