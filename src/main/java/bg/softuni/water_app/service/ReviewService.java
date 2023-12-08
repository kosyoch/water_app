package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.Review;

import java.time.LocalDate;
import java.util.List;

public interface ReviewService {
    void add (ReviewAddBindingModel reviewAddBindingModel, String username, Long gameId);

    void remove(Long id);

    List<Review> getAllReviewsByGame(Game game);

    void removeOldReviews();
}
