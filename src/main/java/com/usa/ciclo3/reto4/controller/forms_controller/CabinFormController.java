package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Cabana;
import com.usa.ciclo3.reto4.model.Category;
import com.usa.ciclo3.reto4.service.CabanaService;
import com.usa.ciclo3.reto4.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formCabin")
public class CabinFormController {

    // inyeccion de dependencias
    @Autowired
    private CabanaService cabinService;
    @Autowired
    private CategoryService categoryService;

    /**
     * valida los campos y si estan correctos persiste la cabaña
     * 
     * @param cabin
     * @param result
     * @param modelo
     * @return path
     */
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarCabanaForm(@Valid @ModelAttribute("cabin") Cabana cabin, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            List<Cabana> cabanas = cabinService.traerTodo();
            List<Category> categorias = categoryService.TraerTodo();
            modelo.addAttribute("categorias", categorias);
            modelo.addAttribute("cabanas", cabanas);

            return "formCabin";
        }
        cabinService.guardarCabana(cabin);
        return "redirect:/formCabin";
    }

    /**
     * Valida los datos al actualizar la entidad seleccionada
     * 
     * @param cabin
     * @param result
     * @return path
     */
    @PostMapping(path = "/update/save")
    public String actualizarCategoriaForm(@Valid Cabana cabin, BindingResult result) {
        if (result.hasErrors()) {
            return "formCabin";
        }
        cabinService.actualizarCabana(cabin);

        return "redirect:/formCabin";
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
        Cabana cabin = cabinService.traerCabana(id).get();
        modelo.addAttribute("cabin", cabin);
        return "updateCabin";
    }

    /**
     * Elimina la entidad seleccionada si existe
     * 
     * @param id
     * @return path
     */
    @GetMapping("/delete/{id}")
    public String eliminarCategoriaForm(@PathVariable("id") int id) {
        Optional<Cabana> cabinDelete = cabinService.traerCabana(id);
        if (cabinDelete.isPresent()) {
            cabinService.eliminarCabana(id);
        }
        return "redirect:/formCabin";
    }

}
