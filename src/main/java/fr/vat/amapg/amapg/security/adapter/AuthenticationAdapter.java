package fr.vat.amapg.amapg.security.adapter;

import fr.vat.amapg.amapg.security.entity.AuthenticatedUser;
import fr.vat.amapg.amapg.user.FindUser;
import fr.vat.amapg.amapg.user.entity.User;
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
