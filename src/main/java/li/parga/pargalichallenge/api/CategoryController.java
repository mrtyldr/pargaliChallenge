package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.business.abstracts.CategoryService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/api/categories")
    DataResult<Category> addCategory(@RequestBody Category category){
        return this.categoryService.addCategory(category);
    }
    @GetMapping("/api/categories")
    public DataResult<List<Category>> findAll(){
        return this.categoryService.findAll();
    }

    @GetMapping("/api/categories/{categoryId}")
    public DataResult<Category> findByCategoryId(@PathVariable int categoryId){
        return this.categoryService.findByCategoryId(categoryId);
    }

}
