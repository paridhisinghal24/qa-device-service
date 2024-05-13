package com.device.qa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.device.qa.GenericRestClient;
import com.device.qa.model.FonoapiResponse;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FonoapiService {
	@Autowired
	private GenericRestClient genericRestClient;
	
	@Value("${spring.fonoapi.url}")
	private String fonoapiURl;
	
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Cacheable(cacheNames = "fonoapiCache", key="#p0")
	public FonoapiResponse getMobileData(String mobileName) throws JsonMappingException, JsonProcessingException {
    	if (mobileName == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    	String str = genericRestClient.getData(fonoapiURl);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		FonoapiResponse fonoapiResponse =  objectMapper.readValue(str, FonoapiResponse.class);
		return fonoapiResponse;
	}
}
