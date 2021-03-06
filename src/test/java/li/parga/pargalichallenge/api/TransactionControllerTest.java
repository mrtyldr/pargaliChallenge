package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.entities.User;
import li.parga.pargalichallenge.repository.TransactionRepository;
import li.parga.pargalichallenge.service.CategoryService;
import li.parga.pargalichallenge.service.TransactionService;
import li.parga.pargalichallenge.service.UserService;
import li.parga.pargalichallenge.entities.Category;
import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void beforeEach() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "categories", "accounts", "users", "transactions");
    }


    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_negative_balance_exception() throws Exception{
        var userResult  =userService.addUser(new User(
                "osmanosman",
                "osman",
                "osmancik",
                "osman@parga.li"
        ));
        categoryService.addCategory(new Category(1,"salary"));
        String request = """
                {
                  "amount": -100,
                  "date": "2022-06-04",
                  "categoryId": 1,
                  "accountId": %d
                }""".formatted(userResult.getData().getAccounts().get(0).getAccountId());
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser("osman@parga.li")
    public void should_throw_category_not_found_exception() throws Exception {
        var userResult = userService.addUser(new User(
                "osmanosman",
                "osman",
                "osmancik",
                "osman@parga.li"
        ));
        String request = """
                {
                  "amount": 100,
                  "date": "2022-06-04",
                  "categoryId": 1,
                  "accountId": %d
                }""".formatted(userResult.getData().getAccounts().get(0).getAccountId());
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_create_transaction() throws Exception{
        var userResult = userService.addUser(new User(
                "osmanosman",
                "osman",
                "osmancik",
                "osman@parga.li"
        ));
        categoryService.addCategory(new Category(1,"salary"));

        String request = """
                {
                  "amount": 100,
                  "date": "2022-06-04",
                  "categoryId": 1,
                  "accountId": %d
                }""".formatted(userResult.getData().getAccounts().get(0).getAccountId());
        assertThat(transactionRepository.count()).isEqualTo(0);
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(transactionRepository.count()).isEqualTo(1);
    }
}

