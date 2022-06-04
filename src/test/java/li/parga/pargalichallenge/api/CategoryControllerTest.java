package li.parga.pargalichallenge.api;

import li.parga.pargalichallenge.business.abstracts.CategoryService;
import li.parga.pargalichallenge.business.abstracts.UserService;
import li.parga.pargalichallenge.business.concretes.CategoryManager;
import li.parga.pargalichallenge.business.concretes.UserManager;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.entities.concretes.Category;
import li.parga.pargalichallenge.entities.concretes.dto.UserWithoutWalletDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserManager userManager;

    @Autowired
    private CategoryManager categoryService;

    @Test
    @WithMockUser("osman@parga.li")
    public void findByCategoryId() throws Exception {
        var category = new SuccessDataResult<>(new Category(1, "test", null));
        when(categoryService.findByCategoryId(1)).thenReturn(category);

        var request = get("/api/categories/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.categoryName", is("test")));

    }

    @Test
    @WithMockUser("osman@parga.li")
    public void should_return_not_found_when_categories_null() throws Exception {
        userManager.addUser(new UserWithoutWalletDto(
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
        userManager.addUser(new UserWithoutWalletDto(
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