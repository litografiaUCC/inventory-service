package com.litografiaartesplanchas.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.repository.MaterialRepository;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepo;

    public boolean saveMaterial(Material material) {
    try {
        materialRepo.save(material);
        return true; 
    } catch (Exception e) {
        e.printStackTrace(); 
        return false;
    }
}
}
