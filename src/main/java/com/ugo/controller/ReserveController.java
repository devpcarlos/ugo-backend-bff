package com.ugo.controller;

import com.ugo.dto.ReserveDTO;
import com.ugo.services.ReserveServiceIMPL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Reserve")
public class ReserveController {
    @Autowired
    ReserveServiceIMPL reserveServiceIMPL;

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

    @PutMapping("/Update/{Id}")
    public ResponseEntity<?>Update(@PathVariable Long Id,@RequestBody ReserveDTO reserveDTO){
        reserveServiceIMPL.Update(Id,reserveDTO);
        return ResponseEntity.ok("Status Updated");
    }


}
