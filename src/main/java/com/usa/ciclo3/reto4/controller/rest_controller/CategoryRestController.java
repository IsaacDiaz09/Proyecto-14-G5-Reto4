package com.usa.ciclo3.reto4.controller.rest_controller;

import java.util.List;

import com.usa.ciclo3.reto4.model.Category;
import com.usa.ciclo3.reto4.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints: 
 * Visualización de Categorías (endpoint: /api/Category/all)
 * Creación de categorías (endpoint: /api/Category/save)
 * Actualizacion de Categorías (endpoint: /api/Category/update)
 */
@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class CategoryRestController {
    
    // Se inyecta la dependencia servicio
    @Autowired
    CategoryService categoryService;

    /**
     * Regresa todas las categorias almacenadas
     * @return List Category
     */
    @GetMapping("/all")
    public List<Category> recuperarCategorias(){
        return categoryService.TraerTodo();
    }

    /**
     * Persiste un objeto de tipo categoria recibido en el JSON
     * @param category
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarCategoria(@RequestBody Category category){
        categoryService.guardarCategoria(category);
    }
    
    /**
     * Actualiza los parametros de un obj categoria recibido en el JSON
     * @param category
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void actualizarCategoria(@RequestBody Category category){
        categoryService.actualizarCategoria(category);
    }

    /**
     * Elimina una categoria por su ID si la encuentra
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCategoria(@PathVariable("id") int id){
        categoryService.eliminarCategoria(id);
    }
}
