package fr.vat.amapg.amapg.user.persistence;

import fr.vat.amapg.amapg.user.entity.User;
import fr.vat.amapg.amapg.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toSet;

@Component
@RequiredArgsConstructor
public class UserMongoAdapter {

    private final UserMongoDao userMongoDao;

    public User findByUsername(String username) {
        UserMongoDto userDto = userMongoDao.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)
        );

        return new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRoles().stream()
                        .map(UserRole::valueOf)
                        .collect(toSet())
        );
    }

}
