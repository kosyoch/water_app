package bg.softuni.water_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class GameKey extends BaseEntity{
    private String key;
    @ManyToOne
    private Game game;
    @OneToOne
    private User owner;
}
