package bg.softuni.water_app.model.dto.review;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class ReviewAddBindingModel {
    @Size(max = 500, message = "Review cannot be more than 500 characters!")
    private String reviewText;
    @PastOrPresent(message = "Date must be in the past or present!")
    private Date dateCreated;

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
