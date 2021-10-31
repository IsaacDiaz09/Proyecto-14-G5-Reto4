package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Cabana;
import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.model.Message;
import com.usa.ciclo3.reto4.service.CabanaService;
import com.usa.ciclo3.reto4.service.ClientService;
import com.usa.ciclo3.reto4.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formMsg")
public class MessageFormController {

    @Autowired
    MessageService msgService;
    @Autowired
    CabanaService cabinService;
    @Autowired
    ClientService clientService;

    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarMensajeForm(@Valid Message message, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            List<Client> clients = clientService.TraerTodo();
            List<Cabana> cabins = cabinService.traerTodo();
            List<Message> messages = msgService.TraerTodo();

            modelo.addAttribute("clients", clients);
            modelo.addAttribute("cabins", cabins);
            modelo.addAttribute("messages", messages);
            return "formMsg";
        }
        msgService.guardarMensaje(message);
        return "redirect:/formMsg";

    }

    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Message message = msgService.traerMensaje(id).get();
        modelo.addAttribute("message", message);
        return "updateMsg";
    }

    @PostMapping(path = "/update/save")
    public String actualizarMensajeForm(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "updateMsg";
        }
        msgService.actualizaMensaje(message);
        return "redirect:/formMsg";
    }

    @GetMapping("/delete/{id}")
    public String eliminarMensajeForm(@PathVariable("id") int id) {
        Optional<Message> messageDelete = msgService.traerMensaje(id);
        if (messageDelete.isPresent()) {
            msgService.eliminarMensaje(id);
        }
        return "redirect:/formMsg";
    }

}
