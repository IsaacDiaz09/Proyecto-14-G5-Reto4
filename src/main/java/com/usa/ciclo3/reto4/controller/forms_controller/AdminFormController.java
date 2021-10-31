package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Admin;
import com.usa.ciclo3.reto4.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/formAdmin")
public class AdminFormController {

    // inyeccion de dependencia
    @Autowired
    AdminService adminService;
    
    /**
     * Para persistir un obj directamente, el boton en el form se encarga de enviar
     * el obj al endpoint de persistencia
     * 
     * @param admin
     * @param result
     * @return String path
     */
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarAdminForm(@Valid Admin admin, BindingResult result,Model modelo) {
        if (result.hasErrors()) {
            List<Admin> admins = adminService.traerTodo();
            modelo.addAttribute("admins", admins);
            return "formAdmin";
        }
        adminService.guardarAdmin(admin);
        return "redirect:/formAdmin";
    }
    
    /**
     * metodo que redirige a actualizar admin
     * 
     * @return path
     */
    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Admin admn = adminService.traerAdmin(id).get();
        modelo.addAttribute("admin", admn);
        return "updateAdmin";
    }

    /**
     * actualiza un admin con los datos recibidos
     * 
     * @param admin
     * @param result
     * @return path
     */
    @PostMapping(path = "/update/save")
    public String actualizarAdminForm(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "updateAdmin";
        }
        adminService.actualizarAdminForm(admin);
        return "redirect:/formAdmin";
    }


    /**
     * elimina un admin
     * 
     * @param id
     * @return path, redirige al formulario principal para ver el cambio
     */
    @GetMapping("/delete/{id}")
    public String eliminarAdminForm(@PathVariable("id") int id) {
        Optional<Admin> admn = adminService.traerAdmin(id);
        if (admn.isPresent()) {
            adminService.eliminarAdmin(id);
        }
        return "redirect:/formAdmin";
    }
}
