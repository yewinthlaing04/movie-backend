package com.ye.movie.moviebackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ye.movie.moviebackend.dto.MovieDto;
import com.ye.movie.moviebackend.service.impl.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> addMovie(@RequestPart String movieDto ,
                                             @RequestPart MultipartFile file) throws IOException {

        MovieDto Dto = convertToMovieDto(movieDto);

        return new ResponseEntity<>(movieService.addMovie( Dto , file ) , HttpStatus.CREATED );
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> fetchMovieWithId(@PathVariable Integer movieId){
        return new ResponseEntity<>(movieService.getMovie(movieId ) , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> fetchAllMovies (){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Integer movieId ,
                                                @RequestPart MultipartFile file ,
                                                @RequestPart String movieDto ) throws IOException {
        if ( file.isEmpty() ) file = null;
        MovieDto dto = convertToMovieDto(movieDto);
        return ResponseEntity.ok(movieService.updateMovie(movieId , dto , file));
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer movieId) throws IOException {

        return ResponseEntity.ok(movieService.deleteMovie( movieId));
    }

    private MovieDto convertToMovieDto(String movieDtoObj ) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(movieDtoObj , MovieDto.class);

    }
}
