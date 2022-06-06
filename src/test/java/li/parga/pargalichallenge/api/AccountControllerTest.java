package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;
import li.parga.pargalichallenge.service.CategoryService;
import li.parga.pargalichallenge.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountControllerTest {
    private MockMvc mockMvc;
    private UserService userService;




}