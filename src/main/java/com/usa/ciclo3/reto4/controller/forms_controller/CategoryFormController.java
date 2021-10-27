package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Category;
import com.usa.ciclo3.reto4.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formCategory")
public class CategoryFormController {
    // inyeccion de dependencia
    @Autowired
    CategoryService categoryService;

    /**
     * Controlador formulario principal, agrega una entidad
     * 
     * @param category
     * @param result
     * @return path
     */
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarCategoriaForm(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "formCategory";
        }
        categoryService.guardarCategoria(category);
        return "redirect:/formCategory";
    }

    /**
     * Valida los datos al actualizar la entidad seleccionada
     * 
     * @param category
     * @param result
     * @return path
     */
    @PostMapping(path = "/update/save")
    public String actualizarCategoriaForm(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "formCategory";
        }
        categoryService.actualizarCategoria(category);
        return "redirect:/formCategory";
    }

    /**
     * dirige al formulario para actualizar
     * 
     * @param id
     * @param modelo
     * @return path editar entidad
     */
    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Category category = categoryService.traerCategoria(id).get();
        modelo.addAttribute("category", category);
        return "updateCategory";
    }

    /**
     * Elimina la entidad seleccionada si existe
     * 
     * @param id
     * @return path
     */
    @GetMapping("/delete/{id}")
    public String eliminarCategoriaForm(@PathVariable("id") int id) {
        Optional<Category> catDelete = categoryService.traerCategoria(id);
        if (catDelete.isPresent()) {
            categoryService.eliminarCategoria(id);
        }
        return "redirect:/formCategory";
    }

}
