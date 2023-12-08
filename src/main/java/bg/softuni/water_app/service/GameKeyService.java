package bg.softuni.water_app.service;

import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.GameKey;

import java.util.List;

public interface GameKeyService {

    void buy(Game game, String username);

    List<GameKey> getMyGameKeys(String username);
}
