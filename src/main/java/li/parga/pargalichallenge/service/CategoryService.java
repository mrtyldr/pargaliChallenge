package li.parga.pargalichallenge.service;


import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.repository.CategoryRepository;
import li.parga.pargalichallenge.entities.Category;
import li.parga.pargalichallenge.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public DataResult<Category> addCategory(Category category) {
        return new SuccessDataResult<>(this.categoryRepository.save(category));
    }


    public DataResult<Category> findByCategoryId(int categoryId) {
        var category = this.categoryRepository.findByCategoryId(categoryId);
        if (category == null)
            throw new NotFoundException("Category doesn't exist");
        return new SuccessDataResult<>(category);
    }


    public DataResult<List<Category>> findAll() {
        var categories = this.categoryRepository.findAll();
        if (categories.size() == 0)
            throw new NotFoundException("There is no predifined category. please add a category before continue");

        return new SuccessDataResult<>(categories);
    }
}
