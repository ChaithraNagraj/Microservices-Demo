package com.javabrains.movieinfo.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabrains.movieinfo.model.Movie;
@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@RequestMapping("/{movieId}")
public Movie getMovieInfo(@PathVariable("movieId") String movieId){
		return new Movie("foo","test movie");
	}
	
}
