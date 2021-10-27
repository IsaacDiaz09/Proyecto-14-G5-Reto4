package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formClient")
public class ClientFormController {
    int ID;

    @Autowired
    ClientService clientService;

    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Client client = clientService.traerCliente(id).get();
        modelo.addAttribute("client", client);
        return "updateClient";
    }

    @PostMapping(path = "/update/save")
    public String actualizarClienteForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "formClient";
        }
        System.out.println(client.getIdClient());

        clientService.actualizaCliente(client);
        return "redirect:/formClient";
    }

    /**
     * Guarda el cliente diligenciado en el formulario
     * 
     * @param client
     * @param result
     * @return
     */
    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarClienteForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "formClient";
        }
        clientService.guardarCliente(client);
        return "redirect:/formClient";
    }

    @GetMapping("/delete/{id}")
    public String eliminarClienteForm(@PathVariable("id") int id) {
        System.out.println(id);
        Optional<Client> clientDelete = clientService.traerCliente(id);
        if (clientDelete.isPresent()) {
            clientService.eliminarCliente(id);
        }
        return "redirect:/formClient";
    }

}
