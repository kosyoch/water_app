package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.service.GameService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games/add")
    public ModelAndView add(@ModelAttribute("gameAddBindingModel")GameAddBindingModel gameAddBindingModel){
        return new ModelAndView("game-add");
    }
    @PostMapping("/games/add")
    public ModelAndView add(
            @ModelAttribute("gameAddBindingModel") @Valid GameAddBindingModel gameAddBindingModel,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("game-add");
        }
        gameService.add(gameAddBindingModel);
        return new ModelAndView("redirect:/home");

    }
}
