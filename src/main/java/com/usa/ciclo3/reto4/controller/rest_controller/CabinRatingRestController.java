package com.usa.ciclo3.reto4.controller.rest_controller;

import java.util.List;

import com.usa.ciclo3.reto4.model.CabinRating;
import com.usa.ciclo3.reto4.service.CabinRatingService;

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

/**
 * -Calificación de las reservas. (endpoint: /api/Score/save)
 */
@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class CabinRatingRestController {

    @Autowired
    CabinRatingService cabinRatingService;

    @GetMapping("/all")
    public List<CabinRating> traerCalificaciones() {
        return cabinRatingService.TraerTodo();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarCalificacion(@RequestBody CabinRating cabinRating) {
            cabinRatingService.guardarCalificacion(cabinRating);
        
    }

}
