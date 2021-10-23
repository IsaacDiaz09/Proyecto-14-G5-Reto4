package com.usa.ciclo3.reto4.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Historia de usuario 3.2: Creacion de categorias
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(max= 45,message="El nombre es demasiado largo")
    @Column(length=45)
    private String name;

    @Size(max = 250,message="La descripción es muy larga")
    @Column(length=250)
    private String description;

    /**
     * Relacion uno - muchos -> cabañas
     */
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Cabana> cabins;

    // Constructores

    public Category(String name, String description, List<Cabana> cabins) {
        this.name = name;
        this.description = description;
        this.cabins = cabins;
    }
    
    public Category(){
        
    }

    // --- Getters y Setters ---
  
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cabana> getCabins() {
        return cabins;
    }

    public void setCabins(List<Cabana> cabins) {
        this.cabins = cabins;
    }
}
