package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.Cabana;
import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.service.CabanaService;
import com.usa.ciclo3.reto4.service.ClientService;
import com.usa.ciclo3.reto4.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formReservation")
public class ReservationFormController {
    
    // se inyectan las dependencias requeridas
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CabanaService cabinService;

    @Autowired
    private ClientService clientService;

    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarReservacionForm(@Valid Reservation reservation, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            List<Reservation> reservaciones = reservationService.traerReservas();
            modelo.addAttribute("reservas", reservaciones);

            List<Client> clientes = clientService.TraerTodo();
            modelo.addAttribute("clientes", clientes);

            List<Cabana> cabanas = cabinService.traerTodo();
            modelo.addAttribute("cabanas", cabanas);

            return "formReservation";
        }
        reservationService.guardarReservacion(reservation);
        return "redirect:/formReservation";
    }

    @GetMapping("/update/{id}")
    public String redireccionActualizar(@PathVariable("id") int id, Model modelo) {
        Reservation reservation = reservationService.traerReserva(id).get();

        modelo.addAttribute("reservation", reservation);
        modelo.addAttribute("Status", getListStatus());

        return "updateReservation";
    }

    @PostMapping(path = "/update/save")
    public String actualizarReservacionForm(@Valid Reservation reservation, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("Status", getListStatus());
            return "updateReservation";
        }

        reservationService.actualizaReserva(reservation);
        return "redirect:/formReservation";
    }

    @GetMapping("/delete/{id}")
    public String eliminarReservacionForm(@PathVariable("id") int id) {
        Optional<Reservation> reservationDelete = reservationService.traerReserva(id);
        if (reservationDelete.isPresent()) {
            reservationService.eliminarReservacion(id);
        }
        return "redirect:/formReservation";
    }


    public List<String> getListStatus() {
        List<String> status = new ArrayList<String>();
        status.add("Programado");
        status.add("Cancelado");
        status.add("Realizado");

        return status;
    }

}
