package bg.softuni.water_app.model.dto.game;

import bg.softuni.water_app.model.entity.enums.CategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Date;

public class GameAddBindingModel {
    @NotBlank(message = "A game title is required!")
    private String title;
    @Size(max = 300, message = "The description must be 300 characters or less!")
    @NotBlank(message = "A game description is required!")
    private String description;
    @PastOrPresent(message = "The release date must be in the past or present!")
    @NotBlank(message = "A release date is required!")
    private Date releaseDate;
    @Positive(message = "The price must be a positive number!")
    @NotBlank(message = "A price is required!")
    private BigDecimal price;
    @NotBlank(message = "You must select a category!")
    private CategoryName category;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }
}
