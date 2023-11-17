package bg.softuni.water_app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table
public class Review extends BaseEntity{
    @Column(nullable = false)
    @Size(min = 1, max = 10)
    private Integer rating;
    @Column(name = "review_text")
    @Size(max = 200)
    private String reviewText;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @ManyToOne
    private Player reviewWriter;


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Player getReviewWriter() {
        return reviewWriter;
    }

    public void setReviewWriter(Player reviewWriter) {
        this.reviewWriter = reviewWriter;
    }
}
