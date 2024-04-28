package com.ugo.controller;

import com.ugo.entitys.external.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/experience")
public class ExperienceController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("{experienceId}")
    public ResponseEntity<?> getExperienceDetails(@PathVariable String experienceId) {
        String baseUrl = "http://ec2-13-38-218-106.eu-west-3.compute.amazonaws.com:8081/experience/";
        Experience experience = restTemplate.getForObject(baseUrl + experienceId, Experience.class);
        return ResponseEntity.ok(experience);
    }
    @GetMapping
    public ResponseEntity<?> getExperiences() {
        String baseUrl = "http://ec2-13-38-218-106.eu-west-3.compute.amazonaws.com:8081/experience/";
        ResponseEntity<Experience[]> experiences = restTemplate.getForEntity(baseUrl, Experience[].class);
        return ResponseEntity.ok(experiences.getBody());
    }
}
