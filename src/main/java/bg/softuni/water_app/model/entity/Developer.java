package bg.softuni.water_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table
public class Developer extends User{
    @OneToMany
    private List<Game> createdGames;

    public List<Game> getCreatedGames() {
        return createdGames;
    }

    public void setCreatedGames(List<Game> createdGames) {
        this.createdGames = createdGames;
    }
}
