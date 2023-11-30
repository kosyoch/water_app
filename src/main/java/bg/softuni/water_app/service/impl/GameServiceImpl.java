package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.repo.CategoryRepository;
import bg.softuni.water_app.repo.GameRepository;;
import bg.softuni.water_app.service.GameService;
import bg.softuni.water_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    private final CategoryRepository categoryRepository;

    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }


    @Override
    public void add(GameAddBindingModel gameAddBindingModel) {
        Category category = categoryRepository.findByName(gameAddBindingModel.getCategory());

        if(category != null){
            Game game = new Game();
            game.setTitle(gameAddBindingModel.getTitle());
            game.setDescription(gameAddBindingModel.getDescription());
            game.setReleaseDate(gameAddBindingModel.getReleaseDate());
            game.setPrice(gameAddBindingModel.getPrice());
            game.setCategory(category);
            game.setDeveloper(userService.getLoggedUser());

            gameRepository.save(game);
        }
    }

    @Override
    public void remove(Long id) {
        gameRepository.deleteById(id);
    }
}
