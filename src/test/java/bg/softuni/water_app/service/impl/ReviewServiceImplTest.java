package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.dto.review.ReviewAddBindingModel;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.Review;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.repository.GameRepository;
import bg.softuni.water_app.repository.ReviewRepository;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.ReviewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewServiceToTest;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @BeforeEach
    void setUp(){
        reviewRepository.deleteAll();
        userRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @AfterEach
    void tearDown(){
        reviewRepository.deleteAll();
        userRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    void testAdd(){
        String username = "testUser";
        Long gameId = 1L;

        userRepository.save(testCustomer(username));

        gameRepository.save(testGame(gameId));

        ReviewAddBindingModel testReviewAddBindingModel = new ReviewAddBindingModel();
        testReviewAddBindingModel.setGameId(gameId);
        testReviewAddBindingModel.setReviewText("Test review text");

        reviewServiceToTest.add(testReviewAddBindingModel, username);

        Review savedReview = reviewRepository.findByReviewText("Test review text");

        assertEquals("Test review text", savedReview.getReviewText());
        assertEquals(LocalDate.now(), savedReview.getDateCreated());
        assertEquals(username, savedReview.getReviewWriter().getUsername());
        assertEquals(gameId, savedReview.getReviewedGame().getId());

    }

    private User testCustomer (String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("test");
        user.setConfirmPassword("test");
        user.setRole(UserRole.CUSTOMER);
        user.setEmail("test@email.com");
        return user;
    }


    private Game testGame(Long id){
        Game game = new Game();
        game.setId(id);
        game.setTitle("Test title");
        return game;
    }

}
