package bg.softuni.water_app.repository;

import bg.softuni.water_app.model.entity.GameKey;
import bg.softuni.water_app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameKeyRepository extends JpaRepository<GameKey, Long> {
    List<GameKey> findAllByOwner(User user);

    List<GameKey> findAllByGame_Id(Long id);
}
