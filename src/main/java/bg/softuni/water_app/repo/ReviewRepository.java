package bg.softuni.water_app.repo;

import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByReviewedGame(Game game);

    Review findByReviewText(String reviewText);

    List<Review> findAllByDateCreatedBefore(LocalDate localDate);

    List<Review> findAllByReviewedGame_Id(Long id);
}
