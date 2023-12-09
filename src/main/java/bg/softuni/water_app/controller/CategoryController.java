package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.exception.ObjectNotFoundException;
import bg.softuni.water_app.service.CategoryService;
import bg.softuni.water_app.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    private final GameService gameService;

    public CategoryController(CategoryService categoryService, GameService gameService) {
        this.categoryService = categoryService;
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryToDisplay(id).orElseThrow(() -> new ObjectNotFoundException("Category with id " + id + " not found!"));
        model.addAttribute("category", category);
        model.addAttribute("games", gameService.getGamesByCategory(category));
        return ("category");
    }
}
