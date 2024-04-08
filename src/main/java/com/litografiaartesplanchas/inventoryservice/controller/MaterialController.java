package com.litografiaartesplanchas.inventoryservice.controller;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.inventoryservice.model.Material;
import com.litografiaartesplanchas.inventoryservice.service.MaterialService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteMaterialById(@PathVariable Integer id) {
        try {
            materialService.deleteMaterialById(id);
            return ResponseEntity.ok().body("{\"status\": 200, \"message\": \"ok\"}");

        } catch (RuntimeErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": 404, \"message\": \"Material Not Found\"}");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400, \"message\": \"Something Went Wrong\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMaterialById(@PathVariable Integer id) {
        try {
            Optional<Material> material = materialService.getMaterialById(id);
            if (material.isPresent()) {
                return ResponseEntity.ok().body(material.get());

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": 404, \"message\": \"Material Not Found\"}");
            }
         
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400, \"message\": \"Something Went Wrong\"}");
        }
    }

    @GetMapping("/type/{name}")
    public ResponseEntity<Object> getMaterialsByType(@PathVariable String name) {
        try {
            List<Material> materials = materialService.getMaterialsByType(name);
            if (materials.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": 404, \"message\": \"Type doesnt exist or wrong type input\"}");
            }
            return ResponseEntity.ok().body(materials);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400,\"message\": \"Something Went Wrong\"}");
        }
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<Object> updateMaterialQuantity(@PathVariable Integer id, @RequestParam int quantity) {
        try {
            materialService.updateMaterialQuantity(id, quantity);
            return ResponseEntity.ok().body("{\"status\": 200, \"message\": \"Quantity updated successfully\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": 404, \"message\": \"Material Not Found\"}");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"status\": 400, \"message\": \"Something Went Wrong\"}");
        }
    }
}
