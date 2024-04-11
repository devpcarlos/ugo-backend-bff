package com.ugo.controller;

import com.ugo.dto.StateDTO;
import com.ugo.services.StateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(stateService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody StateDTO stateDTO, HttpServletRequest request) {
        stateService.save(stateDTO, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StateDTO stateDTO) {
        stateService.update(id, stateDTO);
        return ResponseEntity.ok("STATUS SUCCESSFULLY UPDATED");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stateService.deleteById(id);
        return ResponseEntity.ok("SUCCESSFULLY DELETED STATUS");
    }
}
