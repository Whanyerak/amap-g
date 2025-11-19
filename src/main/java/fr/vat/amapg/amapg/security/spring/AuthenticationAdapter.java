package fr.vat.amapg.amapg.security.spring;

import fr.vat.amapg.amapg.security.authentication.FindUser;
import fr.vat.amapg.amapg.security.authentication.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // TODO : Use in AnnotationFactory
public class AuthenticationAdapter implements UserDetailsService {

    private final FindUser findUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUser.execute(username);

        return new AuthenticatedUser(
                user.username(),
                user.password(),
                user.roles()
        );
    }

}
