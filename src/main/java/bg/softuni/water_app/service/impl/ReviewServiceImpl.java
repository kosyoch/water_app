package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.Review;
import bg.softuni.water_app.repository.GameRepository;
import bg.softuni.water_app.repository.ReviewRepository;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public void add(ReviewAddBindingModel reviewAddBindingModel, String username) {
        Review review = new Review();
        review.setReviewText(reviewAddBindingModel.getReviewText());
        review.setDateCreated(LocalDate.now());
        review.setReviewWriter(userRepository.findByUsername(username));
        review.setReviewedGame(gameRepository.findGameById(reviewAddBindingModel.getGameId()));
        reviewRepository.save(review);
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getAllReviewsByGame(Game game) {
        return reviewRepository.findAllByReviewedGame(game);
    }

    @Override
    public void removeOldReviews() {
        List<Review> reviewsToRemove = reviewRepository.findAllByDateCreatedBefore(LocalDate.now().minusMonths(6));

        if(reviewsToRemove.size() > 0){
            reviewRepository.deleteAll(reviewsToRemove);
            reviewsToRemove.clear();
        }
    }
}
