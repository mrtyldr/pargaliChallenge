package li.parga.pargalichallenge.api.controllers;

import li.parga.pargalichallenge.Business.abstracts.CategoryService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.entities.concretes.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
class CategoryControllerTest {

    private MockMvc mockMvc;
    private CategoryService categoryService;
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        categoryService = Mockito.mock(CategoryService.class);
        categoryController = new CategoryController(categoryService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void findByCategoryId() throws Exception {
        DataResult<Category> category = new SuccessDataResult<>(new Category(1,"test",null));
        Mockito.when(categoryService.findByCategoryId(1)).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/controllers/categorycontroller/findbycategoryid/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.categoryName", is("test")));

    }
}