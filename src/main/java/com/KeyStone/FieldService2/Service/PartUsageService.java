package com.KeyStone.FieldService2.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.PartUsage;
import com.KeyStone.FieldService2.Repository.PartUsageRepository;

@Service
public class PartUsageService {

    @Autowired
    private PartUsageRepository partUsageRepository;

    public PartUsage addPartUsage(PartUsage partUsage) {
        return partUsageRepository.save(partUsage);
    }

    public List<PartUsage> getAllPartUsage() {
        return partUsageRepository.findAll();
    }

    public PartUsage getPartUsageById(Long id) {
        return partUsageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part usage not found"));
    }

    public void deletePartUsage(Long id) {
        partUsageRepository.deleteById(id);
    }
}
