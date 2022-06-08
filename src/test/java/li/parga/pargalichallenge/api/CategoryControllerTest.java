package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.entities.Category;
import li.parga.pargalichallenge.entities.dto.UserWithoutAccountDto;
import li.parga.pargalichallenge.service.CategoryService;
import li.parga.pargalichallenge.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @AfterEach
    public void beforeEach() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "categories", "accounts", "users", "transactions");
    }




    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_not_found_when_categories_null() throws Exception {
        userService.addUser(new UserWithoutAccountDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_categories() throws Exception {
        userService.addUser(new UserWithoutAccountDto(
                "osman",
                "osmancik",
                "123456",
                "osman@parga.li"
        ));
        categoryService.addCategory(new Category(1, "SALARY"));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json("""

                        {
                          "success": true,
                         
                          "data": [
                            {
                              "categoryId": 1,
                              "categoryName": "SALARY"
                            }
                          ]
                        }
                        """));


    }
}