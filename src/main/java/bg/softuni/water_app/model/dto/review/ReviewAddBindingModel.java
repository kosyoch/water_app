package bg.softuni.water_app.model.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewAddBindingModel {
    @Size(max = 500, message = "Review cannot be more than 500 characters!")
    @NotBlank(message = "Review text cannot be blank!")
    private String reviewText;

    private Long gameId;


    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
