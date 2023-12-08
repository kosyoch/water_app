package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.Review;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.exception.ObjectNotFoundException;
import bg.softuni.water_app.service.GameService;
import bg.softuni.water_app.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    private final GameService gameService;


    private Long gameId;

    public ReviewController(ReviewService reviewService, GameService gameService) {
        this.reviewService = reviewService;
        this.gameService = gameService;
    }

    @GetMapping("/add/game{id}")
    public ModelAndView add(@ModelAttribute("reviewAddBindingModel")ReviewAddBindingModel reviewAddBindingModel,@PathVariable("id") Long id){
        this.gameId = id;
        return new ModelAndView("review-add");
    }

    @PostMapping("/add/game{id}")
    public ModelAndView add(@AuthenticationPrincipal User user,
            @ModelAttribute("reviewAddBindingModel") @Valid ReviewAddBindingModel reviewAddBindingModel,
            BindingResult bindingResult){
        String username = user.getUsername();
        if(bindingResult.hasErrors()){
            return new ModelAndView("review-add");
        }
        reviewService.add(reviewAddBindingModel, username, gameId);
        return new ModelAndView("redirect:/home");

    }
    @GetMapping("/remove{id}")
    public ModelAndView remove (@PathVariable("id") Long id){
        reviewService.remove(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/game{id}")
    public String reviewsPage(@PathVariable("id") Long id, Model model){
        Game game = gameService.getGameById(id).orElseThrow(() -> new ObjectNotFoundException("Game with id " + id + " not found!"));
        List<Review> reviews = reviewService.getAllReviewsByGame(game);
        model.addAttribute("reviews", reviews) ;
        model.addAttribute("game", game);
        return "review";
    }


}
