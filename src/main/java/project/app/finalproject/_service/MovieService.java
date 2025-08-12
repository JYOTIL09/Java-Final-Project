package project.app.finalproject._service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.app.finalproject._model.Movie;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    /*The Service Class : This class will handle all the stuff related to api data . All data will be fetched here from the api;

     */
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class); //to log the messages

    //hardcoded token as per the API using documentation
    private final String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzMjQ4NjA4MDZjMDk0ZjJlMTNhNjk3ZDhlZDVjODAzNSIsIm5iZiI6MTc1NDc2MjQxNy43NjYsInN1YiI6IjY4OTc4Y2IxMjgwOTUxZmEyYzkyZWFkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XiucNYqW7Z3ehyLTSw5Q1rCX0Btb98mdYzAIRHGONyk";
    private final String BASE_URL = "https://api.themoviedb.org/3";
    private final RestTemplate restTemplate = new RestTemplate();

    private HttpEntity<String> buildEntity() {
        //This method is to set authorization headers with api calls
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", BEARER_TOKEN);
        headers.set("Accept", "application/json");
        return new HttpEntity<>(headers);
    }

    public List<Movie> getTrendingMovies() {

        // This method will fetch all the movies in trend through-out the week
        String url = BASE_URL + "/trending/movie/week";
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, buildEntity(), Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");

            return results.stream().map(this::mapToMovie).collect(Collectors.toList());

        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("API Error: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            System.err.println(ex.getResponseBodyAsString());
        } catch (RestClientException ex) {
            logger.error("Client Error: {}", ex.getMessage());
            System.err.println(ex.getMessage());

        } catch (Exception ex) {
            logger.error("Unexpected Error: {}", ex.getMessage());
            System.err.println(ex.getMessage());
        }
        return Collections.emptyList();
    }


    public Movie getMovieDetails(Long id) {
        //to fetch individual movie details
        String url = BASE_URL + "/movie/" + id;
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, buildEntity(), Map.class);
            return mapToMovie(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("API Error: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
        } catch (RestClientException ex) {
            logger.error("Client Error: {}", ex.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected Error: {}", ex.getMessage());
        }
        return null;
    }

    private Movie mapToMovie(Map<String, Object> data) {
        //once movies are gotten from API, this method will map them as Movie model of the app
        return new Movie(
                (Integer) data.get("id"),
                (String) data.get("title"),
                (String) data.get("overview"),
                (String) data.get("release_date"),
                data.get("vote_average") != null ? ((Number) data.get("vote_average")).doubleValue() : 0.0,
                "https://image.tmdb.org/t/p/w500" + data.get("poster_path")
        );
    }

    public List<Movie> searchMovies(String query) {
        //Listing movies based on search
        String url = BASE_URL + "/search/movie?query=" + query;
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, buildEntity(), Map.class);
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");
            return results.stream().map(this::mapToMovie).collect(Collectors.toList());
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("API Error: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            System.err.println(ex.getResponseBodyAsString());
        } catch (RestClientException ex) {
            logger.error("Client Error: {}", ex.getMessage());
            System.err.println(ex.getMessage());

        } catch (Exception ex) {
            logger.error("Unexpected Error: {}", ex.getMessage());
            System.err.println(ex.getMessage());
        }
        return Collections.emptyList();
    }
}
