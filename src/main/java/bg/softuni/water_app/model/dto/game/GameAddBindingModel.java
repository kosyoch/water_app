package bg.softuni.water_app.model.dto.game;

import bg.softuni.water_app.model.entity.enums.CategoryName;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;


public class GameAddBindingModel {
    @NotBlank(message = "A game title is required!")
    private String title;
    @Size(max = 300, message = "The description must be 300 characters or less!")
    @NotBlank(message = "A game description is required!")
    private String description;
    @Positive(message = "The price must be a positive number!")
    @NotNull(message = "You must put in a price!")

    private BigDecimal price;

    @NotNull(message = "You must select a category!")
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
