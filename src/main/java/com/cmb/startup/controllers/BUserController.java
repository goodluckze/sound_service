package com.cmb.startup.controllers;


import com.cmb.startup.model.SoundRegistry;
import com.cmb.startup.service.BUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BUserController {

    @Autowired
    public BUserService service;

    @Value("${spring.datasource.url}")
    private String jdbcurl;

    @RequestMapping("")
    public String test(){
        return "hello" + jdbcurl;
    }

    @RequestMapping("/sound")
    public ResponseEntity registerSound(@RequestBody SoundRegistry soundRegistry  /*, UriComponentsBuilder builder*/) {
        String code = service.buildSound(soundRegistry);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",code);
        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
    }

    @RequestMapping("/event")
    public String buildWav(@RequestParam(value = "event_id") String eventId){

        return "";
    }

}
