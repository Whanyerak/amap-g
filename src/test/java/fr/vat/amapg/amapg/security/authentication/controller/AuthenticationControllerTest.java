package fr.vat.amapg.amapg.security.authentication.controller;

import fr.vat.amapg.amapg.security.controller.AuthenticationController;
import fr.vat.amapg.amapg.configuration.bean.MockedModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationControllerTest {

    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        authenticationController = new AuthenticationController();
    }

    @Test
    void shouldReturnLoginView_whenRootingToLogin() {
        // Act
        String viewName = authenticationController.login();

        // Assert
        assertThat(viewName).isEqualTo("security/login");
    }

    @Test
    void shouldReturnLoginViewWithLoginErrorInModel_whenRootingToLoginError() {
        // Arrange
        MockedModel model = new MockedModel();

        // Act
        String viewName = authenticationController.failedLogin(model);

        // Assert
        assertThat(viewName).isEqualTo("security/login");
        assertThat(model.getAttribute("loginError")).isEqualTo(true);
    }

    @Test
    void shouldReturnLogoutView_whenRootingToLogout() {
        // Act
        String viewName = authenticationController.logout();

        // Assert
        assertThat(viewName).isEqualTo("security/logout");
    }

}

