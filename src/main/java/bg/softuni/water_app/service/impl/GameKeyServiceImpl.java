package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.GameKey;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.repository.GameKeyRepository;
import bg.softuni.water_app.repository.GameRepository;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.GameKeyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameKeyServiceImpl implements GameKeyService {
    private final GameKeyRepository gameKeyRepository;

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    public GameKeyServiceImpl(GameKeyRepository gameKeyRepository, GameRepository gameRepository, UserRepository userRepository) {
        this.gameKeyRepository = gameKeyRepository;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void buy(Game game, String username) {
        User customer = userRepository.findByUsername(username);
        User developer = userRepository.findByUsername(game.getDeveloper().getUsername());


        customer.setWallet(customer.getWallet().subtract(game.getPrice()));
        developer.setWallet(developer.getWallet().add(game.getPrice()));
        GameKey gameKey = new GameKey();
        gameKey.setOwner(customer);
        gameKey.setGame(game);
        gameKey.setKey(randomKey());
        gameKeyRepository.save(gameKey);
        userRepository.save(customer);
        userRepository.save(developer);


    }

    @Override
    public List<GameKey> getMyGameKeys(String username) {
        User owner = userRepository.findByUsername(username);
        return gameKeyRepository.findAllByOwner(owner);

    }

    private static String randomKey(){
        UUID key = UUID.randomUUID();
        return key.toString().replaceAll("_","");
    }
}
