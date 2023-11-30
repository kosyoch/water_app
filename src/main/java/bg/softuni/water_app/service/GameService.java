package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.game.GameAddBindingModel;

public interface GameService {

    void add(GameAddBindingModel gameAddBindingModel);

    void remove(Long id);


}
