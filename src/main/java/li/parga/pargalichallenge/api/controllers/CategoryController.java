package li.parga.pargalichallenge.api.controllers;


import li.parga.pargalichallenge.Business.abstracts.CategoryService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controllers/categorycontroller")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addcategory")
    DataResult<Category> addCategory(@RequestBody Category category){
        return this.categoryService.addCategory(category);
    }

    @GetMapping("/findAll")
    public DataResult<List<Category>> findAll(){
        return this.categoryService.findAll();
    }

    @GetMapping("/findbycategoryid/{categoryId}")
    public DataResult<Category> findByCategoryId(@PathVariable int categoryId){
        return this.categoryService.findByCategoryId(categoryId);
    }

}
