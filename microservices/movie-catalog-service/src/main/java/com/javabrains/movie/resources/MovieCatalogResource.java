package com.javabrains.movie.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javabrains.movie.models.CatalogItem;
import com.javabrains.movie.models.Movie;
import com.javabrains.movie.models.UserRating;
import com.netflix.discovery.DiscoveryClient;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;
	//private  WebClient.Builder webClientBuilder;

	 @RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
		 //get all rated movie id's
		 
		 UserRating ratings=restTemplate.getForObject("http://rating-data-service/rathingdata/users/"+userId,UserRating.class);
		 //List<Rating> ratings = Arrays.asList(
    	//	new Rating("1234", 4),
    		//new Rating("5678",3)
    		
    	
    //making APi call using resttemplate
     return ratings.getUserRating().stream().map(rating -> {
    	 Movie movie=restTemplate.getForObject("localhost:8082/movies/"+rating.getMovieId(),Movie.class);
  /*WebClientBuilder.build()
  .get()
  .uri("localhost:8082/movies/"+rating.getMovieId())
  .retrieve()
  .bodyT();
  */
    	return  new CatalogItem(movie.getName(),"Desc",rating.getRating());
     })
     .collect(Collectors.toList());
		 //for each movie ID ,call movie info  service and get deatils
		 
		 
		 //put them all together
		 
		 //return Collections.singletonList(
				//new CatalogItem("Transformer","Test",4)
			//	);
	}
}
