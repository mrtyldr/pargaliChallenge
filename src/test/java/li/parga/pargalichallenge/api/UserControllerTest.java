package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.business.concretes.UserManager;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    UserManager userManager;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "osman@parga.li")
    void should_return_not_found_for_invalid_user() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "hakan@parga.li")
    void should_get_wallets() throws Exception {
        // given
        userManager.addUser(new UserWithoutWalletDto(
                "hakan",
                "baykuşlar",
                "123456",
                "hakan@parga.li"
        ));
        mockMvc.perform(get("/api/wallets"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                           "success":true,
                           "message":null,
                           "data":[
                                 {
                                    "walletId":1,
                                    "balance":0.0,
                                    "accountType":"CASH",
                                    "currency":"TRY"
                                 }
                              ]
                        }
                        """));
        ;
    }

    @Test
    @WithMockUser(username = "hakan@parga.li")
    void should_get_user() throws Exception {
        // given
        userManager.addUser(new UserWithoutWalletDto(
                "hakan",
                "baykuşlar",
                "123456",
                "hakan@parga.li"
        ));

        // when - then
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                           "success":true,
                           "message":null,
                           "data":{
                              "userId":1,
                              "firstName":"hakan",
                              "lastName":"baykuşlar",
                              "email":"hakan@parga.li",
                              "wallets":[
                                 {
                                    "walletId":1,
                                    "balance":0.0,
                                    "accountType":"CASH",
                                    "currency":"TRY"
                                 }
                              ],
                              "role":"USER"
                           }
                        }
                        """));
    }
}