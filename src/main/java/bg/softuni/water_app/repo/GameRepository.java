package bg.softuni.water_app.repo;

import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);

    Game findGameById(Long id);
    List<Game> findAllByDeveloper(User user);
    List<Game> findAllByCategory(Category category);
}
