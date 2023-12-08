package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.service.CategoryService;
import bg.softuni.water_app.service.GameKeyService;
import bg.softuni.water_app.service.GameService;
import bg.softuni.water_app.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    private final GameKeyService gameKeyService;

    private final GameService gameService;

    private final CategoryService categoryService;

    private final UserService userService;

    public HomeController(GameKeyService gameKeyService, GameService gameService, CategoryService categoryService, UserService userService) {
        this.gameKeyService = gameKeyService;
        this.gameService = gameService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model){
        String username = user.getUsername();
        model.addAttribute("currentWallet", userService.getCurrentWallet(username));
        model.addAttribute("myGameKeys",gameKeyService.getMyGameKeys(username) );
        model.addAttribute("myCreatedGames", gameService.getMyCreatedGames(username));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "home";
    }
}
