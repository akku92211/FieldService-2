package com.KeyStone.FieldService2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.KeyStone.FieldService2.Entity.Part;
import com.KeyStone.FieldService2.Service.PartService;

@RestController
@RequestMapping("/api/parts")
@CrossOrigin
public class PartController {

    @Autowired
    private PartService partService;

    // Add Part
    @PostMapping
    public ResponseEntity<Part> addPart(@RequestBody Part part) {
        return ResponseEntity.ok(partService.savePart(part));
    }

    // Get All Parts
    @GetMapping
    public ResponseEntity<List<Part>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    // Get Part By ID
    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    // Update Part
    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(
            @PathVariable Long id,
            @RequestBody Part part) {

        Part existingPart = partService.getPartById(id);

        existingPart.setPartNumber(part.getPartNumber());
        existingPart.setName(part.getName());
        existingPart.setDescription(part.getDescription());
        existingPart.setStockQuantity(part.getStockQuantity());

        return ResponseEntity.ok(partService.savePart(existingPart));
    }

    // Delete Part
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.ok("Part deleted successfully");
    }
}