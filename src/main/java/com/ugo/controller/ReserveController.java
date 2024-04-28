package com.ugo.controller;

import com.ugo.dto.reserveDetailsDto;
import com.ugo.dto.reserveRequestDto;
import com.ugo.entitys.external.Experience;
import com.ugo.services.ReserveService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/save/{Experience_Id}")
    public ResponseEntity<?> save(@RequestBody reserveDetailsDto reserveDTO, @PathVariable String Experience_Id, HttpServletRequest request){
        String baseUrl = "http://ec2-13-38-218-106.eu-west-3.compute.amazonaws.com:8081/experience/{id}";
        String Api_Experience_Id=baseUrl.replace("{id}",Experience_Id);
        Experience experience = restTemplate.getForObject(Api_Experience_Id, Experience.class);
        reserveDTO.setExperience(experience);
        reserveService.Save(reserveDTO,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{Reserve_Id}")
    public ResponseEntity<?>FindById(@PathVariable Long Id){
        reserveRequestDto reserveDTO = reserveService.FindById(Id);
        return ResponseEntity.ok(reserveDTO);
    }
    @GetMapping
    public ResponseEntity<?>FindAll(){
       List<reserveRequestDto>reserves = reserveService.FindAll();
        return ResponseEntity.ok(reserves);
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?>DeleteById(@PathVariable Long Id){
        reserveService.DeleteById(Id);
        return ResponseEntity.ok("Successfully deleted!");
    }
    @PutMapping("/update/{Id}")
    public ResponseEntity<?>Update(@PathVariable Long Id,@RequestBody reserveDetailsDto reserveDTO){
        reserveService.Update(Id,reserveDTO);
        return ResponseEntity.ok("Status Updated");
    }
}
