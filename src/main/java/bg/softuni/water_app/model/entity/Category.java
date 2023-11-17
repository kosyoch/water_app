package bg.softuni.water_app.model.entity;

import bg.softuni.water_app.model.entity.enums.CategoryName;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Category extends BaseEntity{
    @Column
    @Enumerated(EnumType.STRING)
    private CategoryName name;
    @Column

    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Game> games;

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
        this.setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(CategoryName name) {
        String description = "";
        switch (name){
            case FPS -> description = "First-person shooters or FPS games are centered on gun and other weapon-based combat seen through the eyes of the main character. " +
                    "The gameplay can be a realistic war simulation or more fictitious. ";
            case RPG -> description = "In a role-playing game or RPG you take control of a character of party of characters. " +
                    "They develop as you progress and influence an immersive and well-defined world.";
            case SPORTS -> description = "Sports games allow players to play various sports and races. " +
                    "Take control of a single player or the entire team.";
            case STRATEGY -> description = "Strategy games allow you to become a general leading armies or planning cities and countries. " +
                    "Compete to become the best strategist in turn-based and real time games rushing to achieve goals.";
            case SIMULATION -> description = "In simulation games you can either run large scale simulations such as creating and maintaining a city, " +
                    "or on a smaller scale do things which may be hard to achieve in real life such as become a pilot and fly an airplane.";
        }
        this.description = description;
    }
}
