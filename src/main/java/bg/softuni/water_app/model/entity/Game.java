package bg.softuni.water_app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class Game extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String title;

    private String description;
    @Positive
    private BigDecimal price;

    @ManyToOne
    private User developer;

    @OneToMany(mappedBy = "reviewedGame", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    private List<GameKey> gameKeys;

    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<GameKey> getGameKeys() {
        return gameKeys;
    }
}
