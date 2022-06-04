package li.parga.pargalichallenge.repository;

import li.parga.pargalichallenge.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategoryId(int categoryId);
}
