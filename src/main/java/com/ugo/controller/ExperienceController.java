package com.ugo.controller;

import com.ugo.entitys.external.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/experience")
public class ExperienceController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data.api.url}")
    private String dataApiUrl;

    @Value("${data.api.port}")
    private String dataApiPort;

    @GetMapping("{experienceId}")
    public ResponseEntity<?> getExperienceDetails(@PathVariable String experienceId) {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience/";
        Experience experience = restTemplate.getForObject(baseUrl + experienceId, Experience.class);
        return ResponseEntity.ok(experience);
    }

    @GetMapping
    public ResponseEntity<?> getExperiences() {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience/";
        ResponseEntity<Experience[]> experiences = restTemplate.getForEntity(baseUrl, Experience[].class);
        return ResponseEntity.ok(experiences.getBody());
    }
}
