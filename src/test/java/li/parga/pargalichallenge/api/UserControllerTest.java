package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.service.UserService;
import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    UserService userService;

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
    void should_get_accounts() throws Exception {
        // given
        userService.addUser(new UserWithoutAccountDto(
                "hakan",
                "baykuşlar",
                "123456",
                "hakan@parga.li"
        ));
        mockMvc.perform(get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "success": true,
                          "message": null,
                          "data": {
                            "firstName": "hakan",
                            "lastName": "baykuşlar",
                            "accounts": [
                              {
                                "accountId": 1,
                                "balance": 0,
                                "accountType": "CASH",
                                "currency": "TRY"
                              }
                            ],
                            "total": "0.0 TRY"
                          }
                        }
                        """));

    }

    @Test
    @WithMockUser(username = "hakan@parga.li")
    void should_get_user() throws Exception {
        // given
        userService.addUser(new UserWithoutAccountDto(
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
                              "email":"hakan@parga.li"                            
                              
                           }
                        }
                        """));
    }

    @Test
    void should_return_bad_request_for_invalid_email() throws Exception {
        var request = "{\n" +
                "  \"firstName\": \"osman\",\n" +
                "  \"lastName\": \"osmancik\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"email\": \"osman\"\n" +
                "}";

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_not_found_for_invalid_account_id() throws Exception {
        userService.addUser(new UserWithoutAccountDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));
        userService.addUser(new UserWithoutAccountDto(
                "Ahmet",
                "Hakan",
                "123456",
                "ahmet@hakan.com"
        ));
        mockMvc.perform(delete("/api/account?accountId=2"))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_internal_server_error_for_non_exist_wallet() throws Exception {
        userService.addUser(new UserWithoutAccountDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));

        mockMvc.perform(delete("/api/account?accountId=2"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_delete_account() throws Exception {
        userService.addUser(new UserWithoutAccountDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));

        mockMvc.perform(delete("/api/account?accountId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                          {
                          "success": true,
                          "message": "account with id:1 has succesfully been deleted.",
                          "data": null
                        }
                        """));
    }

    @Test
    public void should_add_user() throws Exception {
        var request = "{\n" +
                "  \"firstName\": \"osman\",\n" +
                "  \"lastName\": \"osmancik\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"email\": \"osman@parga.li\"\n" +
                "}";

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "success": true,
                          "message": null,
                          "data": {
                            "userId": 1,
                            "firstName": "osman",
                            "lastName": "osmancik",
                         
                            "email": "osman@parga.li",
                            "accounts":null,
                            "role": "USER"
                          }
                          }
                        """));
    }
}