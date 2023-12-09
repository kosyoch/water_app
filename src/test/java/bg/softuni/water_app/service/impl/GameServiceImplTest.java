package bg.softuni.water_app.service.impl;


import bg.softuni.water_app.model.dto.game.GameAddBindingModel;
import bg.softuni.water_app.model.entity.Category;
import bg.softuni.water_app.model.entity.Game;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.entity.enums.CategoryName;
import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.repository.CategoryRepository;
import bg.softuni.water_app.repository.GameRepository;
import bg.softuni.water_app.repository.UserRepository;
import bg.softuni.water_app.service.GameService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameServiceImplTest {

    @Autowired
    private GameService gameServiceToTest;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;



    @BeforeEach
    void setUp(){
        gameRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }
    @AfterEach
    void tearDown(){
        gameRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testAdd(){
        String username = "testUser";
        userRepository.save(testDeveloper(username));

        CategoryName categoryName = CategoryName.RPG;
        categoryRepository.save(testCategory(categoryName));

        gameServiceToTest.add(testGameAddBindingModel(categoryName), username);

        Game savedGame = gameRepository.findByTitle("Test Game").get();
        assertEquals("Test Game", savedGame.getTitle());
        assertEquals("Lorem ipsum", savedGame.getDescription());
        assertEquals(0, new BigDecimal(10).compareTo(savedGame.getPrice()));
        assertEquals(categoryName, savedGame.getCategory().getName());
        assertEquals(username, savedGame.getDeveloper().getUsername());
    }

    private User testDeveloper(String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("test");
        user.setConfirmPassword("test");
        user.setRole(UserRole.DEVELOPER);
        user.setEmail("test@email.com");
        return user;
    }

    private GameAddBindingModel testGameAddBindingModel(CategoryName categoryName){
        GameAddBindingModel gameAddBindingModel = new GameAddBindingModel();
        gameAddBindingModel.setTitle("Test Game");
        gameAddBindingModel.setDescription("Lorem ipsum");
        gameAddBindingModel.setPrice(BigDecimal.valueOf(10));
        gameAddBindingModel.setCategory(categoryName);
        return gameAddBindingModel;
    }

    private Category testCategory(CategoryName categoryName){
        Category category = new Category();
        category.setName(categoryName);
        return category;
    }
}
