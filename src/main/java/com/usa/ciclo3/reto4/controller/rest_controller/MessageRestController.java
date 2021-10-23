package com.usa.ciclo3.reto4.controller.rest_controller;

import com.usa.ciclo3.reto4.model.Message;
import com.usa.ciclo3.reto4.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
/**
 * Endpoints: Creación de mensajes (endpoint: /api/Message/save) Visualización
 * de mensajes (endpoint: /api/Message/all)
 */
public class MessageRestController {

    @Autowired
    MessageService msgService;

    @GetMapping("/all")
    public List<Message> recuperarMensajes() {
        return msgService.TraerTodo();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarMensaje(@RequestBody Message msg) {
        msgService.guardarMensaje(msg);

    }

}
