package li.parga.pargalichallenge.Business.concretes;

import li.parga.pargalichallenge.Business.abstracts.CategoryService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataAccess.abstracts.CategoryDao;
import li.parga.pargalichallenge.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;
    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public DataResult<Category> addCategory(Category category) {
       return new SuccessDataResult<>(this.categoryDao.save(category));
    }

    @Override
    public DataResult<Category> findByCategoryId(int categoryId) {
        return new SuccessDataResult<>(this.categoryDao.findByCategoryId(categoryId));
    }

    @Override
    public DataResult<List<Category>> findAll() {
        return new SuccessDataResult<>(this.categoryDao.findAll());
    }
}
