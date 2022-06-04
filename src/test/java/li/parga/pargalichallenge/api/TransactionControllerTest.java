package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.business.concretes.CategoryManager;
import li.parga.pargalichallenge.business.concretes.TransactionsManager;
import li.parga.pargalichallenge.business.concretes.UserManager;
import li.parga.pargalichallenge.entities.concretes.Category;
import li.parga.pargalichallenge.entities.concretes.dto.TransactionWithWalletsId;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionsManager transactionsManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private CategoryManager categoryManager;


    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_negative_balance_exception() throws Exception{
        userManager.addUser(new UserWithoutWalletDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));
        categoryManager.addCategory(new Category(1,"salary"));
        String request = "{\n" +
                "  \"amount\": -100,\n" +
                "  \"date\": \"2022-06-04\",\n" +
                "  \"categoryId\": 1,\n" +
                "  \"walletId\": 1\n" +
                "}";
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser("osman@parga.li")
    public void should_throw_not_found_exception() throws Exception {
        userManager.addUser(new UserWithoutWalletDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));
        String request = "{\n" +
                "  \"amount\": 100,\n" +
                "  \"date\": \"2022-06-04\",\n" +
                "  \"categoryId\": 1,\n" +
                "  \"walletId\": 1\n" +
                "}";
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_make_transaction() throws Exception{
        userManager.addUser(new UserWithoutWalletDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));
        categoryManager.addCategory(new Category(1,"salary"));
        String request = "{\n" +
                "  \"amount\": 100,\n" +
                "  \"date\": \"2022-06-04\",\n" +
                "  \"categoryId\": 1,\n" +
                "  \"walletId\": 1\n" +
                "}";
        mockMvc.perform(post("/api/transaction").contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

