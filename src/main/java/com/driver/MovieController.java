package com.driver;

import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    MovieService movieService = new MovieService();

    @PostMapping("/add-movie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        if(movieService.addMovie(movie)) return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        return new ResponseEntity<Error>(HttpStatus.CONFLICT);
    }
    @PostMapping("/add-director")
    public ResponseEntity<?> addDirector(@RequestBody Director director){
        if(movieService.addDirector(director)) return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        return new ResponseEntity<Error>(HttpStatus.CONFLICT);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<?> addMovieDirectorPair(@RequestParam("movie name")String movie,
                                                  @RequestParam("director name")String director){
        movieService.addDirectorAndMovie(movie,director);
        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name")String movie){
        return ResponseEntity.ok(movieService.getMovie(movie));
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name")String director){
        return ResponseEntity.ok(movieService.getDirector(director));
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director")String director){
        return ResponseEntity.ok(movieService.getMovieListForDirector(director));
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<Authenticator.Success> deleteDirectorByName(@RequestParam("director name")String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("delete-all-directors")
    public ResponseEntity<Authenticator.Success> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
