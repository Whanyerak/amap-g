package fr.vat.amapg.amapg.authentication.login;

import fr.vat.amapg.amapg.authentication.login.entity.AuthenticatedUser;
import fr.vat.amapg.amapg.authentication.persistence.UserMongoDao;
import fr.vat.amapg.amapg.authentication.persistence.UserMongoDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoginService implements UserDetailsService {

    private final UserMongoDao userMongoDao;

    public LoginService(UserMongoDao userMongoDao) {
        this.userMongoDao = userMongoDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserMongoDto userDto = userMongoDao.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + userName)
        );

        return new AuthenticatedUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRoles()
        );
    }

}
