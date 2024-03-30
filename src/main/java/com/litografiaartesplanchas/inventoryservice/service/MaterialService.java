package com.litografiaartesplanchas.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.repository.MaterialRepository;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepo;

    public ResponseEntity<Object> saveMaterial(@RequestBody Material material) {
        try {
            boolean savedSuccessfully = materialService.saveMaterial(material);
            if (savedSuccessfully) {
                return ResponseEntity.ok().body("{\"status\": 200, \"message\": \"ok\"}");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400, \"message\": \"Failed to save material\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400, \"message\": \"Something Went Wrong\"}");
        }
    }
}
