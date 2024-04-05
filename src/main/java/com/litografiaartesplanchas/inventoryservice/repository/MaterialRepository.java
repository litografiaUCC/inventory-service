package com.litografiaartesplanchas.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litografiaartesplanchas.inventoryservice.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    boolean existByName(String name);

}