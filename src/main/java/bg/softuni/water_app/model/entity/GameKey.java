package bg.softuni.water_app.model.entity;

import jakarta.persistence.*;

@Table
@Entity
public class GameKey extends BaseEntity{
    @Column(name = "game_key")
    private String key;
    @ManyToOne
    @JoinColumn
    private Game game;
    @OneToOne
    @JoinColumn
    private User owner;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
