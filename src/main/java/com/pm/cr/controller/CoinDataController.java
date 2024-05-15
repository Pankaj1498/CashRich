package com.pm.cr.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CoinDataController {

	@Value("${coinmarketcap.api.key}")
	private String apiKey; // Inject API key from application properties

	private final RestTemplate restTemplate;

	public CoinDataController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/coin-data")
	public ResponseEntity<String> getLatestCryptoQuotes(@RequestParam(required = false) String symbol) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("X-CMC_PRO_API_KEY", apiKey);

		String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + "BTC,ETHLTC";

		try {
			URI uri = new URI(url);
			RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			String response = restTemplate.exchange(requestEntity, String.class).getBody();
			return ResponseEntity.ok(response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}
}
