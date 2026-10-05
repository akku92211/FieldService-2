package com.KeyStone.FieldService2.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.Part;
import com.KeyStone.FieldService2.Repository.PartRepository;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    // Add Part
    public Part savePart(Part part) {

        if (part.getCreatedAt() == null) {
            part.setCreatedAt(LocalDateTime.now());
        }

        return partRepository.save(part);
    }

    // Get All Parts
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    // Get Part By ID
    public Part getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
    }

    // Delete Part
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }
}