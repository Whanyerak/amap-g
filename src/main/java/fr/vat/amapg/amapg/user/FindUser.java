package fr.vat.amapg.amapg.user;

import fr.vat.amapg.amapg.user.entity.User;
import fr.vat.amapg.amapg.user.persistence.UserMongoAdapter;
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
