package com.pm.cr.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.cr.model.Status;
import com.pm.cr.model.USD;
import com.pm.cr.repository.CoinDataRepository;
import com.pm.cr.repository.CryptoDataRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CoinDataController {

	@Value("${coinmarketcap.api.key}")
	private String apiKey;
	private final RestTemplate restTemplate;

	@Autowired
	private CryptoDataRepository cryptoDataRepository;

	@Autowired
	private CoinDataRepository coinDataRepository;

	public CoinDataController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/coin-data")
	public ResponseEntity<String> getLatestCryptoQuotes(@RequestParam(required = false) String symbol)
			throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("X-CMC_PRO_API_KEY", apiKey);

		String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + "BTC,ETHLTC";

		try {
			URI uri = new URI(url);
			RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			String response = restTemplate.exchange(requestEntity, String.class).getBody();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response);
			JsonNode status = rootNode.get("status");
			JsonNode data = rootNode.get("data");
			JsonNode btc = data.get("BTC");
			JsonNode quote = btc.get("quote");
			JsonNode usd = quote.get("USD");
			System.out.println(status.toString());
			System.out.println("root node" + rootNode.toString());
			Status cryptoData = mapper.treeToValue(status, Status.class);
			USD usds = mapper.treeToValue(usd, USD.class);
			cryptoDataRepository.save(cryptoData);
			coinDataRepository.save(usds);

			return ResponseEntity.ok(response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}
}
