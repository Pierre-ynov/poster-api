package com.webservices.posterapi.daos;

import com.webservices.posterapi.models.Poster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPosterDao extends MongoRepository<Poster, String> {
	@Query("{id : ?0}")
	Poster findPosterByID (String id);
	
	@Query("{contentId : ?0}")
	List<Poster> findPostersByIdFilm (String contentId);
	
	@Query("{contentId : ?0, timeOfDay : ?1}")
	List<Poster> findPostersByIdFilm (String contentId, String timeOfDay);
}
