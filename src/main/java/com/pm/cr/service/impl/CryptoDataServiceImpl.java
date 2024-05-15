package com.pm.cr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.cr.model.CryptoData;
import com.pm.cr.repository.CryptoDataRepository;
import com.pm.cr.service.CryptoDataService;

@Service
public class CryptoDataServiceImpl implements CryptoDataService {

	@Autowired
	CryptoDataRepository cryptoDataRepository;

	@Override
	public void insertCryptoData(String response) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode rootNode = objectMapper.readTree(response);
			JsonNode dataNode = rootNode.path("data");
			Iterator<JsonNode> elements = dataNode.elements();
			List<CryptoData> cryptoDataList = new ArrayList<>();
			while (elements.hasNext()) {
				JsonNode cryptoNode = elements.next();
				CryptoData cryptoData = parseCryptoData(cryptoNode);
				cryptoDataList.add(cryptoData);
			}
			cryptoDataRepository.saveAll(cryptoDataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private CryptoData parseCryptoData(JsonNode cryptoNode) {
		CryptoData cryptoData = new CryptoData();
		return cryptoData;

	}
}
