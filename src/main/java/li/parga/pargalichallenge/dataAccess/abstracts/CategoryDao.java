package li.parga.pargalichallenge.dataAccess.abstracts;

import li.parga.pargalichallenge.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {
    Category findByCategoryId(int categoryId);
}
