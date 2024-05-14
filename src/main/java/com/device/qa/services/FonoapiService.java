package com.device.qa.services;

import java.util.Collections;

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
	
	@Value("${spring.fonoapi.get-device.url}")
	private String fonoapiDeviceURl;
	
	@Value("${spring.fonoapi.get-device.url}")
	private String fonoapiTokenURl;
	
	// This is the key which you get when you sign up for fonoapi on the website
	@Value("${spring.fonoapi.key}")
	private String fonoapiKey;
	
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Cacheable(cacheNames = "fonoapiCache", key="#p0", unless="#result == null")
	public FonoapiResponse getMobileData(String mobileName) throws JsonMappingException, JsonProcessingException {
    	try {
	    	if (mobileName == null) {
	            throw new IllegalArgumentException("Key cannot be null");
	        }
	    	String token = getToken();
	        String url = fonoapiDeviceURl + "?token=" + token + "&device=" + mobileName;
	    	String str = genericRestClient.getData(url);
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			FonoapiResponse fonoapiResponse =  objectMapper.readValue(str, FonoapiResponse.class);
			return fonoapiResponse;
    	} catch (Exception e) {
    		System.out.println("Error occured with fonoapi service : " + e.getMessage());
    		return null;
    	}
	}

	private String getToken() {
    	return genericRestClient.postData(fonoapiDeviceURl,Collections.singletonMap("token", fonoapiKey));
	}
}
