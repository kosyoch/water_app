package bg.softuni.water_app.model.dto.review;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class ReviewAddBindingModel {
    @Size(max = 500, message = "Review cannot be more than 500 characters!")
    private String reviewText;


    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
