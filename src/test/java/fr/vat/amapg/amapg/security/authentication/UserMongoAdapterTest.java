package fr.vat.amapg.amapg.security.authentication;

import fr.vat.amapg.amapg.user.persistence.UserMongoAdapter;
import fr.vat.amapg.amapg.user.persistence.UserMongoDao;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserMongoAdapterTest {

    private UserMongoAdapter userMongoAdapter;

    @Mock
    private UserMongoDao userMongoDao;

    @BeforeEach
    void setUp() {
        userMongoAdapter = new UserMongoAdapter(userMongoDao);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String notExistingUsername = "not_existing_user";

        // Act
        ThrowingCallable execution = () -> userMongoAdapter.findByUsername(notExistingUsername);

        // Assert
        assertThatThrownBy(execution)
            .isInstanceOf(UsernameNotFoundException.class)
            .hasMessageContaining("User not found: not_existing_user");
    }

}