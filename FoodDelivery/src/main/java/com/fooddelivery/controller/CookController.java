package com.fooddelivery.controller;

import com.fooddelivery.model.Cook;
import com.fooddelivery.service.CookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cooks")
public class CookController {

    @Resource
    private CookService cookService;

    @GetMapping
    public ResponseEntity<List<Cook>> getAllCooks() {
        return ResponseEntity.ok(cookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cook> getCookById(@PathVariable Integer id) {
        return ResponseEntity.of(Optional.ofNullable(cookService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<Cook> addCook(@RequestBody Cook cook) {
        return ResponseEntity.ok(cookService.save(cook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cook> updateCook(@RequestBody Cook cook) {
        return ResponseEntity.ok(cookService.save(cook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cook> deleteCookById(@PathVariable Integer id) {
        return ResponseEntity.of(Optional.ofNullable(cookService.deleteById(id)));
    }
}
