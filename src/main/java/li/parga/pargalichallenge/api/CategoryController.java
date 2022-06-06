package li.parga.pargalichallenge.api;


import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.Category;
import li.parga.pargalichallenge.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/api/categories")
    DataResult<Category> addCategory(@RequestBody Category category) {
        return this.categoryService.addCategory(category);
    }

    @GetMapping("/api/categories")
    public DataResult<List<Category>> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/api/categories/{categoryId}")
    public DataResult<Category> findByCategoryId(@PathVariable int categoryId) {
        return this.categoryService.findByCategoryId(categoryId);
    }


}
