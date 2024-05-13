package com.device.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.device.qa.model.Mobile;
import com.device.qa.services.MobileService;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;

    @PostMapping
    public ResponseEntity<Mobile> register(@RequestBody Mobile mobile) {
        return ResponseEntity.ok(mobileService.save(mobile));
    }
    
    @GetMapping
    public ResponseEntity<List<Mobile>> getAllMobiles() {
        return ResponseEntity.ok(mobileService.getAll());
    }
    

//    @GetMapping(path = "/{mobile-name}")
//    public ResponseEntity<Mobile> getPersonById(@PathVariable(name = "mobile-name", required = true) String mobileName) {
//        Mobile mobile = mobileService.findById(personId);
//        if (person != null) {
//            return ResponseEntity.ok(person);
//        }
//        return ResponseEntity.notFound().build();
//    }


}
