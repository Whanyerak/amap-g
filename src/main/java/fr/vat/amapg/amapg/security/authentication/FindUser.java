package fr.vat.amapg.amapg.security.authentication;

import fr.vat.amapg.amapg.security.persistence.UserMongoAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUser {

    private final UserMongoAdapter userMongoAdapter;

    public User execute(String username) {
        return userMongoAdapter.findByUsername(username);
    }

}
