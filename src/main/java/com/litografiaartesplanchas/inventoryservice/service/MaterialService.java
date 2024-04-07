package com.litografiaartesplanchas.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.repository.MaterialRepository;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepo;

    public void saveMaterial(Material material) {
        String materialName = material.getName();
        if (materialRepo.existsByName(material.getName())) {
            throw new RuntimeErrorException(new Error("Material '" + materialName + "' already exists"));
        } 

        materialRepo.save(material);
    }

    public List<Material> getAll(){
		return materialRepo.findAll();
    }

    public void deleteMaterialById(Integer id) {
        if (!materialRepo.existsById(id)) {
            throw new RuntimeErrorException(new Error("Material doesn't exist"));
        }

        materialRepo.deleteById(id);
    }

    public Optional<Material> getMaterialById (Integer id) {
        return materialRepo.findById(id);
    }

}