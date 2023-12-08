package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.repo.*;
;
import bg.softuni.water_app.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final GameKeyRepository gameKeyRepository;

    private final ReviewRepository reviewRepository;



    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository, UserRepository userRepository, GameKeyRepository gameKeyRepository, ReviewRepository reviewRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.gameKeyRepository = gameKeyRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public void add(GameAddBindingModel gameAddBindingModel, String username) {
        Category category = categoryRepository.findByName(gameAddBindingModel.getCategory());

        if(category != null){
            Game game = new Game();
            game.setTitle(gameAddBindingModel.getTitle());
            game.setDescription(gameAddBindingModel.getDescription());
            game.setPrice(gameAddBindingModel.getPrice());
            game.setCategory(category);
            game.setDeveloper(userRepository.findByUsername(username));

            gameRepository.save(game);

        }
    }

    @Override
    public void remove(Game game) {
        Long id = game.getId();
        gameKeyRepository.deleteAll(gameKeyRepository.findAllByGame_Id(id));
        reviewRepository.deleteAll(reviewRepository.findAllByReviewedGame_Id(id));
        gameRepository.deleteById(id);
    }

    @Override
    public List<Game> getMyCreatedGames(String username) {
        User developer = userRepository.findByUsername(username);
        return gameRepository.findAllByDeveloper(developer);
    }

    @Override
    public Optional<Game> getGameById(Long id) {
        return Optional.ofNullable(gameRepository.findGameById(id));
    }

    @Override
    public List<Game> getGamesByCategory(Category category) {
        return gameRepository.findAllByCategory(category);
    }
}
