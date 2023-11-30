package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;
import bg.softuni.water_app.model.entity.Review;
import bg.softuni.water_app.repo.ReviewRepository;
import bg.softuni.water_app.service.ReviewService;
import bg.softuni.water_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final UserService userService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    @Override
    public void add(ReviewAddBindingModel reviewAddBindingModel) {
        Review review = new Review();
        review.setReviewText(reviewAddBindingModel.getReviewText());
        review.setDateCreated(reviewAddBindingModel.getDateCreated());
        review.setReviewWriter(userService.getLoggedUser());
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }
}
