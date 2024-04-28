package com.ugo.controller;

import com.ugo.dto.StateDTO;
import com.ugo.services.StateServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateServiceImpl stateServiceImpl;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(stateServiceImpl.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stateServiceImpl.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StateDTO stateDTO, HttpServletRequest request) {
        stateServiceImpl.save(stateDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StateDTO stateDTO) {
        stateServiceImpl.update(id, stateDTO);
        return ResponseEntity.ok("STATUS SUCCESSFULLY UPDATED");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stateServiceImpl.deleteById(id);
        return ResponseEntity.ok("SUCCESSFULLY DELETED STATUS");
    }
}
