package com.litografiaartesplanchas.inventoryservice.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.model.TypeMaterial;
import com.litografiaartesplanchas.inventoryservice.repository.MaterialRepository;
import com.litografiaartesplanchas.inventoryservice.repository.TypeMaterialRepository;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private TypeMaterialRepository typeMaterialRepo;

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

    public List<Material> getMaterialsByType(String name) {
        return materialRepo.findByTypeMaterialName(name);
    }

    public void updateMaterialQuantity(Integer id, int quantity) {
        Optional<Material> optionalMaterial = materialRepo.findById(id);
        
        if (optionalMaterial.isEmpty()) {
            throw new IllegalArgumentException("Material doesn't exist");
        }

        Material material = optionalMaterial.get();
        material.setQuantity(quantity);
        materialRepo.save(material);
    }

    public void updateMaterial(Material updateMaterial) {
        Optional<Material> optionalMaterial = materialRepo.findById((Integer) updateMaterial.getId());
        if (optionalMaterial.isEmpty()) {
            throw new IllegalArgumentException("Material doesn't exist");
        }
    
        Material material = optionalMaterial.get();
        
        if (updateMaterial.getName() != null)  
            material.setName(updateMaterial.getName());
        
        if (updateMaterial.getPrice() != 0) 
            material.setPrice(updateMaterial.getPrice());
        
        if (updateMaterial.getQuantity() != 0) 
            material.setQuantity(updateMaterial.getQuantity());
    
        if (updateMaterial.getTypeMaterial() != null) {
            Optional<TypeMaterial> typeUpdate = typeMaterialRepo.findById((Integer) updateMaterial.getTypeMaterial().getId());
            if (typeUpdate.isEmpty()) {
                throw new IllegalArgumentException("Type Material invalid");
            } else {
                material.setTypeMaterial(typeUpdate.get());
            }
        }
        materialRepo.save(material);
    }
}  