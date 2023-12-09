package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.exception.ObjectNotFoundException;
import bg.softuni.water_app.service.GameService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/add")
    public ModelAndView showAddGamePage(@ModelAttribute("gameAddBindingModel")GameAddBindingModel gameAddBindingModel){
        return new ModelAndView("game-add");
    }

    @GetMapping("/{id}")
    public String showGamePage (@PathVariable("id") Long id, Model model){
        Game game = gameService.getGameById(id).orElseThrow(() -> new ObjectNotFoundException("Game with id " + id + " not found!"));
        model.addAttribute("game", game);
        return "game";
    }

    @PostMapping()
    public ModelAndView add(@AuthenticationPrincipal User user,
            @ModelAttribute("gameAddBindingModel") @Valid GameAddBindingModel gameAddBindingModel,
            BindingResult bindingResult){
        String username = user.getUsername();
        if(bindingResult.hasErrors()){
            return new ModelAndView("game-add");
        }
        gameService.add(gameAddBindingModel, username);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/{id}/remove")
    public ModelAndView remove (@PathVariable("id") Long id){
        Game game = gameService.getGameById(id).orElseThrow(() -> new ObjectNotFoundException("Game with id " + id + " not found!"));
        gameService.remove(game);
        return new ModelAndView("redirect:/home");
    }

}
