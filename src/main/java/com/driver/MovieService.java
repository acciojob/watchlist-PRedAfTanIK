package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    MovieRepository movieRepository = new MovieRepository();

    public String addMovie(Movie movie){
        if(movieRepository.addMovie(movie)) return "Movie Added Successfully";
        return "There was some error while adding the movie";
    }
    public String addDirector(Director director){
        if(movieRepository.addDirector(director)) return "Director added successfully";
        return "There was some error while adding the director";
    }
    public void addDirectorAndMovie(String movie,String director){
        movieRepository.addMovieToDirector(movie,director);
    }
    public Movie getMovie(String movie){
        return movieRepository.getMovie(movie);
    }
    public Director getDirector(String director){
        return movieRepository.getDirector(director);
    }

    public List<String> getMovieListForDirector(String director){
        return movieRepository.getMoviesOfDirector(director);
    }
    public List<String> getAllMovies(){
        return movieRepository.getAllMovies();
    }
    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }
    public void deleteAllDirectors(){
        List<String> directorsList = movieRepository.getAllDirectors();
        for(String director:directorsList)
            movieRepository.deleteDirector(director);
    }
}
