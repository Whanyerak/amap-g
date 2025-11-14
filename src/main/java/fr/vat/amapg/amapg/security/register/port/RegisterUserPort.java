package fr.vat.amapg.amapg.security.register.port;

import fr.vat.amapg.amapg.security.register.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface RegisterUserPort {

    void registerUser(User user, String seed);

}
