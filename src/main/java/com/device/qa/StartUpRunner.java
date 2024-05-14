package com.device.qa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.device.qa.model.FonoapiResponse;
import com.device.qa.model.Mobile;
import com.device.qa.services.FonoapiService;
import com.device.qa.services.MobileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class StartUpRunner implements CommandLineRunner {

	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private FonoapiService fonoapiService;

	
    public void run(String... args) throws JsonMappingException, JsonProcessingException {
    	List<Mobile> listMobiles = mobileService.getAll();
    	for(Mobile mobile : listMobiles) {
			if(mobile.getMobileName() != null) {
    			FonoapiResponse fonoapiResponse = fonoapiService.getMobileData(mobile.getMobileName());
			}
    	}    	
    }

}