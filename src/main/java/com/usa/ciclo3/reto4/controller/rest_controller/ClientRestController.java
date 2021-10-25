package com.usa.ciclo3.reto4.controller.rest_controller;

import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Endpoints: Creación de clientes (endpoint: /api/Client/save) Visualización de
 * Clientes (endpoint: /api/Client/all)
 */
@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })

public class ClientRestController {

    // Se inyecta el servicio
    @Autowired
    ClientService clientService;

    /**
     * regresa una lista con todos los clientes
     * @return List Client
     */
    @GetMapping("/all")
    public List<Client> recuperarClientes() {
        return clientService.TraerTodo();
    }

    /**
     * Persiste un obj de tipo cliente
     * @param client
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarCliente(@RequestBody Client client) {
        clientService.guardarCliente(client);
    }

    /**
     * Actualiza un obj de tipo cliente con sus repectivas validaciones en el servicio
     * @param client
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void actualizaCliente(@RequestBody Client client){
        clientService.actualizaCliente(client);
    }

    /**
     * Elimina un obj de tipo cliente segun su ID
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCliente(@PathVariable("id") int id){
        clientService.eliminarCliente(id);
    }

}
