package bg.softuni.water_app.service;

import bg.softuni.water_app.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryToDisplay(Long id);
}
