package project.app.finalproject._model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class FavouriteMovie {

    @Id
    @Getter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String posterUrl;

    public FavouriteMovie() {

    }

    public FavouriteMovie(Integer id, String title, String posterUrl) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
    }

}
