package com.ugo.controller;

import com.ugo.dto.ReserveDTO;
import com.ugo.entitys.external.Experience;
import com.ugo.services.ReserveServiceIMPL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reserve")
public class ReserveController {
    @Autowired
    ReserveServiceIMPL reserveServiceIMPL;
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/{reserve_id}/{experience_id}")
    public ResponseEntity<?>create( @PathVariable long reserve_id,@PathVariable String experience_id,
                                   @RequestBody ReserveDTO reserveDTO,HttpServletRequest request){
        String URL = "http://ec2-13-38-218-106.eu-west-3.compute.amazonaws.com:8081/experience/{id}";
       String Api_Url_Id = URL.replace("{id}",experience_id);
       Experience experience = restTemplate.getForObject(Api_Url_Id, Experience.class);
       reserveDTO.setExperience(experience);
       ReserveDTO reserve = reserveServiceIMPL.FindById(reserve_id);
       return ResponseEntity.ok(reserve);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ReserveDTO reserveDTO, HttpServletRequest request){
        reserveServiceIMPL.Save(reserveDTO,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<?>FindAll(){
       List<ReserveDTO>reserves =reserveServiceIMPL.FindAll();
        return ResponseEntity.ok(reserves);
    }
    @GetMapping("/{Id}")
    public ResponseEntity<?>FindById(@PathVariable Long Id){
      ReserveDTO reserveDTO = reserveServiceIMPL.FindById(Id);
        return ResponseEntity.ok(reserveDTO);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<?>DeleteById(@PathVariable Long Id){
        reserveServiceIMPL.DeleteById(Id);
        return ResponseEntity.ok("Successfully deleted!");
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<?>Update(@PathVariable Long Id,@RequestBody ReserveDTO reserveDTO){
        reserveServiceIMPL.Update(Id,reserveDTO);
        return ResponseEntity.ok("Status Updated");
    }


}
