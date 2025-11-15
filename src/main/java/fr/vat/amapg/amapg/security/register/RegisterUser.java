package fr.vat.amapg.amapg.security.register;

import fr.vat.amapg.amapg.security.register.entity.User;
import fr.vat.amapg.amapg.security.register.port.RegisterUserPort;
import fr.vat.amapg.amapg.userprofile.CreateUserProfile;
import fr.vat.amapg.amapg.userprofile.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUser {

    private final RegisterUserPort registerUserPort;
    private final CreateUserProfile createUserProfile;

    public void execute(User user) {
        String seed = user.email();

        String userId = registerUserPort.registerUser(user, seed);

        var userProfile = new UserProfile(
                userId,
                user.firstName(),
                user.lastName(),
                user.address(),
                user.postalCode(),
                user.city(),
                user.phoneNumber(),
                user.email()
        );

        createUserProfile.execute(userProfile);
    }

}
