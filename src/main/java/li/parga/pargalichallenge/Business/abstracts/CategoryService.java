package li.parga.pargalichallenge.Business.abstracts;

import li.parga.pargalichallenge.core.utilities.results.DataResult;
import li.parga.pargalichallenge.entities.concretes.Category;
import java.util.List;
public interface CategoryService {
    DataResult<Category> addCategory(Category category);

    DataResult<Category> findByCategoryId(int categoryId);

    DataResult<List<Category>> findAll();

}
