package com.litografiaartesplanchas.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litografiaartesplanchas.inventoryservice.model.TypeMaterial;

@Repository
public interface TypeMaterialRepository extends JpaRepository<TypeMaterial, Integer> {
    
}
