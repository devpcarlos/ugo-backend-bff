package com.ugo.controller;

import com.ugo.dto.ReserveDetailsDto;
import com.ugo.dto.reserveRequestDto;
import com.ugo.entitys.external.Experience;
import com.ugo.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/reserve")
public class ReserveController {

    @Autowired
    ReserveService reserveService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${data.api.url}")
    private String dataApiUrl;

    @Value("${data.api.port}")
    private String dataApiPort;

    @PostMapping("{experienceId}")
    public ResponseEntity<?> reserveExperience(@RequestBody ReserveDetailsDto reserveDTO, @PathVariable String experienceId) {
        String baseUrl = dataApiUrl + ":" + dataApiPort + "/experience/" + experienceId;
        Experience experience = restTemplate.getForObject(baseUrl, Experience.class);
        reserveDTO.setExperience(experience);
        reserveService.save(reserveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{Reserve_Id}")
    public ResponseEntity<?> FindById(@PathVariable Long Id) {
        reserveRequestDto reserveDTO = reserveService.FindById(Id);
        return ResponseEntity.ok(reserveDTO);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<reserveRequestDto> reserves = reserveService.findAll();
        return ResponseEntity.ok(reserves);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?> DeleteById(@PathVariable Long Id) {
        reserveService.DeleteById(Id);
        return ResponseEntity.ok("Successfully deleted!");
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<?> Update(@PathVariable Long Id, @RequestBody ReserveDetailsDto reserveDTO) {
        reserveService.Update(Id, reserveDTO);
        return ResponseEntity.ok("Status Updated");
    }
}
