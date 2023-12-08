package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    void add(GameAddBindingModel gameAddBindingModel, String username);

    void remove(Game game);

    List<Game> getMyCreatedGames(String username);

    Optional<Game> getGameById(Long id);

    List<Game> getGamesByCategory(Category category);


}
