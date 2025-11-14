package fr.vat.amapg.amapg.security;

import fr.vat.amapg.amapg.configuration.MongoTestConfiguration;
import fr.vat.amapg.amapg.security.persistence.UserMongoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Import(MongoTestConfiguration.class)
@AutoConfigureMockMvc
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        mongoTemplate.dropCollection("user");
        mongoTemplate.save(
                new UserMongoDto(
                        UUID.randomUUID(),
                        "testuser",
                        passwordEncoder.encode("password"),
                        Set.of("USER")
                )
        );
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnSuccessLogin() throws Exception {
        mvc.perform(formLogin("/login").user("testuser").password("password")).andExpect(authenticated());
    }

    @Test
    public void userLoginFailed() throws Exception {
        mvc.perform(formLogin("/login").user("testuser").password("wrongpassword")).andExpect(unauthenticated());
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

