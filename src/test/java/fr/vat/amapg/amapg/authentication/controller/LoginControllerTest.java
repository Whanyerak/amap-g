package fr.vat.amapg.amapg.authentication.controller;

import fr.vat.amapg.amapg.authentication.persistence.UserMongoDao;
import fr.vat.amapg.amapg.authentication.persistence.UserMongoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static fr.vat.amapg.amapg.authentication.UserRole.ADMIN;
import static fr.vat.amapg.amapg.authentication.UserRole.USER;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserMongoDao userMongoDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        userMongoDao.deleteAll();
        userMongoDao.save(new UserMongoDto(UUID.randomUUID(), "testuser", passwordEncoder.encode("password"), Set.of(USER)));
        userMongoDao.save(new UserMongoDto(UUID.randomUUID(), "testadmin", passwordEncoder.encode("password"), Set.of(ADMIN)));
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        mvc.perform(get("/auth/login")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnSuccessLogin() throws Exception {
        mvc.perform(formLogin("/auth/login").user("testuser").password("password")).andExpect(authenticated());
    }

    @Test
    public void userLoginFailed() throws Exception {
        mvc.perform(formLogin("/auth/login").user("user").password("wrongpassword")).andExpect(unauthenticated());
    }

/*    @Test
    @WithMockUser
    public void shouldReturnUserPage() throws Exception {
        mvc.perform(get("/auth/user")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturn403Forbidden() throws Exception {
        mvc.perform(get("/auth/admin")).andExpect(status().isForbidden());
    }*/

}

