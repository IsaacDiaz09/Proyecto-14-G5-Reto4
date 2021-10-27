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
    // inyeccion de dependencia
    @Autowired
    ClientService clientService;

    /**
     * redirige al form para actualizar la entidad
     * 
     * @param id
     * @param modelo
     * @return path
     */
    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Client client = clientService.traerCliente(id).get();
        modelo.addAttribute("client", client);
        return "updateClient";
    }

    /**
     * actuliza el obj cliente con los datos recibios del form
     * 
     * @param client
     * @param result
     * @return path form principal
     */
    @PostMapping(path = "/update/save")
    public String actualizarClienteForm(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "updateClient";
        }
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

    /**
     * elimina el cliente y redirige al form principal
     * 
     * @param id
     * @return path
     */
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
