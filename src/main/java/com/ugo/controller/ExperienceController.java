package com.ugo.controller;

import com.ugo.entitys.external.Experience;
import com.ugo.entitys.external.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/experience")
public class ExperienceController {

    private static final Logger logger = LoggerFactory.getLogger(ExperienceController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${data.api.url}")
    private String dataApiUrl;

    @Value("${data.api.port}")
    private String dataApiPort;

    @GetMapping("{experienceId}")
    public ResponseEntity<?> getExperienceDetails(@PathVariable String experienceId) {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience/";
        try {
            Experience experience = restTemplate.getForObject(baseUrl + experienceId, Experience.class);
            return ResponseEntity.ok(experience);
        } catch (HttpClientErrorException e) {
            logger.error("Se solicitó una experiencia que no existe: {}", experienceId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la experiencia solicitada.");
    }

    @GetMapping
    public ResponseEntity<?> getExperiences(@RequestParam(required = false) String category) {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience";
        if (category != null) {
            baseUrl += "?category=" + category;
        }
        ResponseEntity<Experience[]> experiences = restTemplate.getForEntity(baseUrl, Experience[].class);

        // ************************************************************************
        // BORRAR LA LINEA SIGUIENTE CUANDO EL DATA API DEVUELVA EL CAMPO IMAGE_URL
        List<Experience> experiencesWithImages = Arrays
                .stream(experiences.getBody())
                .map(
                        experience -> new Experience(
                                experience._id(),
                                experience.name(),
                                experience.type(),
                                experience.description(),
                                experience.country(),
                                experience.province(),
                                experience.price_min(),
                                experience.price_max(),
                                "https://assets.zyrosite.com/cdn-cgi/image/format=auto,w=1200,h=630,fit=crop,f=jpeg/mk3Ew9MwlbcqZJba/img-YbNxzkRwGaHlxOXV.jpg"))
                .toList();
        // ************************************************************************

        return ResponseEntity.ok(experiencesWithImages);
    }

    @GetMapping("{experienceId}/review")
    public ResponseEntity<?> getReviews(@PathVariable String experienceId) {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience/";
        Review[] reviews = restTemplate.getForObject(baseUrl + experienceId + "/review", Review[].class);
        return ResponseEntity.ok(reviews);
    }
}
