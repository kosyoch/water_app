package bg.softuni.water_app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Date;


@Entity
@Table
public class Review extends BaseEntity{
    @Column(name = "review_text")
    @Size(max = 200)
    private String reviewText;
    @Column(name = "date_created")
    private Date dateCreated;
    @ManyToOne
    private User reviewWriter;

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

    public User getReviewWriter() {
        return reviewWriter;
    }

    public void setReviewWriter(User reviewWriter) {
        this.reviewWriter = reviewWriter;
    }
}
