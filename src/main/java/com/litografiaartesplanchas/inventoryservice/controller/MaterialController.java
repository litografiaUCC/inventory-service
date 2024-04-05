package com.litografiaartesplanchas.inventoryservice.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.service.MaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/inventory")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveMaterial(@RequestBody Material material) {
    try {
        materialService.saveMaterial(material);
        
        return ResponseEntity.ok().body("{\"status\": 200, \"message\": \"Material saved successfully\"}");
        
    } catch (RuntimeErrorException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"status\": 409, \"message\": \"Material already exists\"}");
        
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400,\"message\": \"Something Went Wrong\"}");
    }
}



    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try {
            List<Material> materials = materialService.getAll();
            if(materials.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(materials);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400,\"message\": \"Something Went Wrong\"}");
        }
    }
}