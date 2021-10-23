package com.usa.ciclo3.reto4.controller.rest_controller;

import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.service.ReservationService;
import java.util.List;
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

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE})
/**
 * Endpoints: Visualización de Reservas con sus calificaciones. (endpoint:
 * /api/Reservation/all) Creación de Reservas. (endpoint: /api/Reservation/save)
 */
public class ReservationRestController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> recuperarReservaciones() {
        return reservationService.TraerTodo();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarReservacion(@RequestBody Reservation reservation) {
        reservationService.guardarReservacion(reservation);

    }
}
