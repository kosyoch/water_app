package bg.softuni.water_app.repository;

import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryName categoryName);


    Category findCategoryById(Long id);
}
