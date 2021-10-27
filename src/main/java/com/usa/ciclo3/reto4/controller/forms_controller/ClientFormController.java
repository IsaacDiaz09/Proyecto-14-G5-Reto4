package com.usa.ciclo3.reto4.controller.forms_controller;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("formClient")
public class ClientFormController {
    @Autowired
    ClientService clientService;

    /**
     * Guarda el cliente diligenciado en el formulario
     * @param client
     * @param result
     * @return
     */
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarAdminForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "formClient";
        }
        clientService.guardarCliente(client);
        return "redirect:/formClient";
    }

}
