package fr.vat.amapg.amapg.security.register;

import fr.vat.amapg.amapg.security.register.entity.User;
import fr.vat.amapg.amapg.security.register.port.RegisterUserPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterUser {

    private final RegisterUserPort registerUserPort;

    public RegisterUser(RegisterUserPort registerUserPort) {
        this.registerUserPort = registerUserPort;
    }

    public void execute(User user) {
        String seed = user.firstName() + user.lastName() + user.email();

        registerUserPort.registerUser(user, seed);
    }

}
