package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.exception.ObjectNotFoundException;
import bg.softuni.water_app.service.GameKeyService;
import bg.softuni.water_app.service.GameService;
import bg.softuni.water_app.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game-keys")
public class GameKeyController {
    private final GameKeyService gameKeyService;
    private final GameService gameService;

    private final UserService userService;

    public GameKeyController(GameKeyService gameKeyService, GameService gameService, UserService userService) {
        this.gameKeyService = gameKeyService;
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping("/buy")
    public String buy(@RequestParam() Long gameId, @AuthenticationPrincipal User user, Model model) {
        String username = user.getUsername();
        Game game = gameService.getGameById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("Game with id " + gameId + " not found!"));
        boolean canBuyGame = userService.getCurrentWallet(username).compareTo(game.getPrice()) >= 0;
        model.addAttribute("game", game);
        model.addAttribute("canBuyGame", canBuyGame);
        model.addAttribute("currentWallet", userService.getCurrentWallet(username));
        return ("buy");
    }

    @GetMapping("/finalize")
    public ModelAndView finalizePurchase(@RequestParam() Long gameId, @AuthenticationPrincipal User user) {
        String username = user.getUsername();
        Game game = gameService.getGameById(gameId)
                .orElseThrow(() -> new ObjectNotFoundException("Game with id " + gameId + " not found!"));
        gameKeyService.buy(game, username);
        return new ModelAndView("redirect:/home");
    }

}
