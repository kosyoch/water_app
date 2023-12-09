package bg.softuni.water_app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;


@Entity
@Table
public class Review extends BaseEntity{
    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @ManyToOne
    private User reviewWriter;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game reviewedGame;

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

    public User getReviewWriter() {
        return reviewWriter;
    }

    public void setReviewWriter(User reviewWriter) {
        this.reviewWriter = reviewWriter;
    }

    public Game getReviewedGame() {
        return reviewedGame;
    }

    public void setReviewedGame(Game reviewedGame) {
        this.reviewedGame = reviewedGame;
    }
}
