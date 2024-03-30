package com.litografiaartesplanchas.inventoryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "material")
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private int id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String name;

    @Column(name = "precio", nullable = false)
    private int price;

    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_tipo_material")
    private TypeMaterial typeMaterial;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public TypeMaterial getMaterial() {
		return typeMaterial;
	}

	public void setTypeMaterial(TypeMaterial typeMaterial) {
		this.typeMaterial = typeMaterial;
	}
	
}