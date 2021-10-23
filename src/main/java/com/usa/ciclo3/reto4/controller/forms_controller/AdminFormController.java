package com.usa.ciclo3.reto4.controller.forms_controller;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Admin;
import com.usa.ciclo3.reto4.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formAdmin")
public class AdminFormController {
    @Autowired
    AdminService adminService;

    // Para persistir un obj directamente, el boton en el form se encarga de enviar
    // el obj al endpoint de persistencia
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarClienteForm(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "formAdmin";
        }
        adminService.guardarAdmin(admin);
        return "redirect:/";
    }

}
