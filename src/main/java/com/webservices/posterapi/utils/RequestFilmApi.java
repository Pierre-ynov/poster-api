package com.webservices.posterapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestFilmApi {
	
	@Autowired
	private static RestTemplate restTemplate;
	
}
