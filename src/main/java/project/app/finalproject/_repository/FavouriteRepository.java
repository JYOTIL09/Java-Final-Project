package project.app.finalproject._repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.app.finalproject._model.FavouriteMovie;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteMovie, Integer> {

}
