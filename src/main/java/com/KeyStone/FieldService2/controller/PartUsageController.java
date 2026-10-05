package com.KeyStone.FieldService2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.KeyStone.FieldService2.Entity.PartUsage;
import com.KeyStone.FieldService2.Service.PartUsageService;

@RestController
@RequestMapping("/api/part-usage")
@CrossOrigin
public class PartUsageController {

    @Autowired
    private PartUsageService partUsageService;

    // Add Part Usage
    @PostMapping
    public ResponseEntity<PartUsage> addPartUsage(@RequestBody PartUsage partUsage) {
        return ResponseEntity.ok(partUsageService.addPartUsage(partUsage));
    }

    // Get All Part Usage
    @GetMapping
    public ResponseEntity<List<PartUsage>> getAllPartUsage() {
        return ResponseEntity.ok(partUsageService.getAllPartUsage());
    }

    // Get Part Usage By ID
    @GetMapping("/{id}")
    public ResponseEntity<PartUsage> getPartUsageById(@PathVariable Long id) {
        return ResponseEntity.ok(partUsageService.getPartUsageById(id));
    }

    // Delete Part Usage
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePartUsage(@PathVariable Long id) {
        partUsageService.deletePartUsage(id);
        return ResponseEntity.ok("Part usage deleted successfully");
    }
}


