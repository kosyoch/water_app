package bg.softuni.water_app.init;

import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.enums.CategoryName;
import bg.softuni.water_app.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CategoryInit implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoryInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        long count  = categoryRepository.count();

        if (count == 0){
            List<Category> categories = new ArrayList<>();
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                        categories.add(category);
                    });
            categoryRepository.saveAll(categories);
        }
    }
}
