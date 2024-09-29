package com.ye.movie.moviebackend.service.impl;

import com.ye.movie.moviebackend.dto.MovieDto;
import com.ye.movie.moviebackend.entity.Movie;
import com.ye.movie.moviebackend.repository.MovieRepository;
import com.ye.movie.moviebackend.service.IMovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    private final FileService fileService;

    public MovieService(MovieRepository movieRepository , FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Value("${project.poster}")
    private String path ;

    @Value("${base.url}")
    private String url;

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {

        // upload the file
        String uploadFilename = fileService.uploadFile( path , file );

        // set the value of field poster as filename
        movieDto.setPoster(uploadFilename);

        // map dto to movie object
        Movie movie = new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );

        // save movie object to the db -> saved movie object
        Movie savedMovie = movieRepository.save(movie);

        // generate the poster url
        String posterUrl = url + "/file/" + uploadFilename ;

        // map movie object to dto object and return it
        return new MovieDto(
          savedMovie.getMovieId(),
          savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );
    }

    @Override
    public MovieDto getMovie(Integer movieId) {

        // chech the movie in db and if exist , fetch the data , if not return exception
        Movie movie = movieRepository.findById(movieId).orElseThrow( () ->  new RuntimeException("Movie Not Found"));

        // generate posterUrl
        String posterUrl = url + "/file/" + movie.getPoster();

        // map to movie dto object and return it

        return new MovieDto(
                movie.getMovieId() ,
                movie.getTitle() ,
                movie.getDirector() ,
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
    }

    @Override
    public List<MovieDto> getAllMovies() {

        // fetch all the movies from db
        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();

        // iterate the lists
        for ( Movie movie : movies ) {
            String posterUrl = url + "/file/" + movie.getPoster();
            MovieDto moviedto = new MovieDto(
                    movie.getMovieId() ,
                    movie.getTitle() ,
                    movie.getDirector() ,
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            );
            movieDtos.add(moviedto);
        }

        return movieDtos;
    }
}
