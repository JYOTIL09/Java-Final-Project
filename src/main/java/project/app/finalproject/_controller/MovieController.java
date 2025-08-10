package project.app.finalproject._controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.app.finalproject._model.FavouriteMovie;
import project.app.finalproject._model.Movie;
import project.app.finalproject._repository.FavouriteRepository;
import project.app.finalproject._service.MovieService;

import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private FavouriteRepository favouriteRepo;

    @GetMapping("/")
    public String home(Model model) {
        //getting movies from api
        List<Movie> _movies = movieService.getTrendingMovies();

        //passing movies list and no of movies to view template
        model.addAttribute("movies", _movies);
        model.addAttribute("count", _movies.toArray().length);

        return "index";
    }

    @GetMapping("/movie/{id}")
    public String detail(@PathVariable Long id, Model model) {
        //getting movies from api by id and passing details to view template
        Movie movie = movieService.getMovieDetails(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFavourite", favouriteRepo.existsById(movie.getId()));
        return "detail";
    }

    @PostMapping("/favorite/add")
    public String addFavorite(@RequestParam Integer id, @RequestParam String title, @RequestParam String posterUrl) {
        //Adding selected movie in favourite movies repository
        favouriteRepo.save(new FavouriteMovie(id, title, posterUrl));
        return "redirect:/favorites";
    }

    @PostMapping("/favorite/remove")
    public String removeFavorite(@RequestParam Integer id) {
        //Removing selected movie from favourite movies repository
        favouriteRepo.deleteById(id);
        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        //Listing out favourite movies from repository
        List<FavouriteMovie> _movies = favouriteRepo.findAll();
        model.addAttribute("favourites", favouriteRepo.findAll());
        model.addAttribute("count", _movies.toArray().length);
        return "favorites";
    }

    @PostMapping("/search")
    public String searchMovies(@RequestParam("query") String query, Model model) {
        //Searching for the movies and passing list to view template
        List<Movie> _movies = movieService.searchMovies(query);
        model.addAttribute("movies", _movies);
        model.addAttribute("searchQuery", query);
        model.addAttribute("count", _movies.toArray().length);
        return "index";
    }


}
