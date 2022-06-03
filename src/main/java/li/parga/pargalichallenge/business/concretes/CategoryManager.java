package li.parga.pargalichallenge.business.concretes;

import li.parga.pargalichallenge.business.abstracts.CategoryService;
import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.core.utilities.results.SuccessDataResult;
import li.parga.pargalichallenge.dataaccess.abstracts.CategoryDao;
import li.parga.pargalichallenge.entities.concretes.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryDao categoryDao;


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
