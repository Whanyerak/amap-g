package fr.vat.amapg.amapg.security.authentication;

import fr.vat.amapg.amapg.security.UserRole;
import fr.vat.amapg.amapg.security.authentication.entity.AuthenticatedUser;
import fr.vat.amapg.amapg.security.persistence.UserMongoDao;
import fr.vat.amapg.amapg.security.persistence.UserMongoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toSet;

@Component
@RequiredArgsConstructor // TODO : Use in AnnotationFactory
public class LoginService implements UserDetailsService {

    private final UserMongoDao userMongoDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserMongoDto userDto = userMongoDao.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + userName)
        );

        return new AuthenticatedUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRoles().stream()
                        .map(UserRole::valueOf)
                        .collect(toSet())
        );
    }

}
