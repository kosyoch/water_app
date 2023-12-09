package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.repository.CategoryRepository;
import bg.softuni.water_app.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {

        return new ArrayList<>(categoryRepository.findAll());
    }

    @Override
    public Optional<Category> getCategoryToDisplay(Long id) {
        return Optional.ofNullable(categoryRepository.findCategoryById(id));
    }
}
