package com.usa.ciclo3.reto4.controller.rest_controller;

import java.util.List;

import com.usa.ciclo3.reto4.model.Cabana;
import com.usa.ciclo3.reto4.service.CabanaService;

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

/**
 * Endpoints: 
 * Listar cabañas (endpoint: /api/Cabin/all)
 * Creacióon de Cabaña (endpoint: /api/Cabin/save)
 * Actualizar una Cabaña (endpoint: /api/Cabin/update)
 * Eliminacion de una Cabaña (endpoint: /api/Cabin/{id})
 */
@RestController
@RequestMapping("/api/Cabin")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })

public class CabanaRestController {
    @Autowired
    CabanaService cabinService;

    @GetMapping("/all")
    public List<Cabana> recuperarCabanas() {
        return cabinService.traerTodo();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarCabana(@RequestBody Cabana cabin) {
        cabinService.guardarCabana(cabin);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void actualizarCabana(@RequestBody Cabana cabin){
        cabinService.actualizarCabana(cabin);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCabana(@PathVariable("id") int id){
        cabinService.eliminarCabana(id);
    }

}
