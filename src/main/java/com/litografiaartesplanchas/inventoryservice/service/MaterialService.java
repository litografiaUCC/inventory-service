package com.litografiaartesplanchas.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.repository.MaterialRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepo;

    public ResponseEntity<Object> saveMaterial(Material material) {
        try {
            boolean existByName = materialRepo.existByName(material.getName());
            if (existByName) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"status\": 409, \"message\": \"Material already exists\"}");
            } else {
                Material savedMaterial = materialRepo.save(material);
                if (savedMaterial != null) {
                    return ResponseEntity.ok().body("{\"status\": 200, \"message\": \"Material saved successfully\"}");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"status\": 400, \"message\": \"Failed to save material\"}");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("{\"status\": 400, \"message\": \"Something Went Wrong\"}");
        }
    }


    public List<Material> getAll(){
		return materialRepo.findAll();
    }
}





