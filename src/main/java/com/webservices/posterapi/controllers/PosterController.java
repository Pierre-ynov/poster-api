package com.webservices.posterapi.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.webservices.posterapi.daos.IPosterDao;
import com.webservices.posterapi.models.Poster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class PosterController {
	
	@Autowired
	IPosterDao posterDao;
	
	@GetMapping("/posters")
	public ResponseEntity<List<Poster>> getAllPosters () {
		return ResponseEntity.ok(posterDao.findAll());
	}
	
	@GetMapping("/poster/{id}")
	public ResponseEntity<Poster> getPosterById (String id) {
		return ResponseEntity.ok(posterDao.findPosterByID(id));
	}
	
	@GetMapping("/poster/byContentId/{contentId}")
	public ResponseEntity<List<Poster>> getPosterByTime (@PathVariable String contentId) {
		return ResponseEntity.ok(posterDao.findPostersByIdFilm(contentId));
	}
	
	@GetMapping("/poster/byContentId/{contentId}/{timeOfDay}")
	public ResponseEntity<Poster> getPosterByTime (@PathVariable String contentId, @PathVariable String timeOfDay) {
		List<Poster> posters      = posterDao.findPostersByIdFilm(contentId, timeOfDay);
		Poster       randomPoster = posters.get(new Random().nextInt(posters.size()));
		return ResponseEntity.ok(randomPoster);
	}
	
	@PostMapping("/poster")
	public ResponseEntity<Poster> createPoster (@RequestBody ObjectNode jsonPoster) {
		Poster poster = new Poster(jsonPoster);
		return ResponseEntity.ok(posterDao.save(poster));
	}
	
	@PostMapping("/poster/update/{id}")
	public ResponseEntity<Poster> updatePoster (@RequestBody ObjectNode jsonPoster, @PathVariable String id) {
		Poster poster = getPosterById(id).getBody();
		assert poster != null;
		poster.updatePoster(jsonPoster);
		poster.setId(id);
		return ResponseEntity.ok(posterDao.save(poster));
	}
	
	@DeleteMapping("/poster/delete/{id}")
	public ResponseEntity<?> deletePosterById (@PathVariable String id) {
		Poster poster = getPosterById(id).getBody();
		assert poster != null;
		posterDao.save(poster.deletePoster());
		return ResponseEntity.ok("Le poster a bien été \"supprimé\"");
	}
}
