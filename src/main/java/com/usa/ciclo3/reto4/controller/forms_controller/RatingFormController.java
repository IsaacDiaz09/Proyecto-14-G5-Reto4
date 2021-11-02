package com.usa.ciclo3.reto4.controller.forms_controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.CabinRating;
import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.service.CabinRatingService;
import com.usa.ciclo3.reto4.service.ReservationService;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formRatingReservation")
public class RatingFormController {
//	int idReservation;

    @Autowired
    private CabinRatingService reservationRateService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
    public String guardarCalificacion(@Valid CabinRating cabinRating, BindingResult result, Model modelo) {
        if (result.hasErrors()) {
            List<CabinRating> calificaciones = reservationRateService.TraerTodo();
            modelo.addAttribute("calificaciones", calificaciones);

            List<Reservation> reservaciones = reservationService.traerReservas();
            List<Reservation> reservacionesNoCalificadas = new ArrayList<>();
            for (Reservation reservacion : reservaciones) {
                if (Objects.isNull(reservacion.getScore())) {
                    reservacionesNoCalificadas.add(reservacion);
                }
            }
            modelo.addAttribute("reservaciones", reservacionesNoCalificadas);
            modelo.addAttribute("rating", new CabinRating());
            return "formRatingReservation";
        }

        reservationRateService.guardarCalificacion(cabinRating);
        return "redirect:/formRatingReservation";
    }

    @GetMapping("/update/{id}")
    public String formEditRating(@PathVariable("id") Integer id, Model modelo) {
        CabinRating rating = reservationRateService.traerCalificacionReserva(id).get();
        modelo.addAttribute("revervationRating", rating);

        return "updateRatingForm";
    }

    @PostMapping("/update/save")
    public String editarCalificacion(@Valid CabinRating cabinRating, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "updateRatingForm";
        }
        reservationRateService.actualizarCalificacion(cabinRating);

        return "redirect:/formRatingReservation";
    }

    @GetMapping("/delete/{id}")
    public String eliminarReservacionForm(@PathVariable("id") int id) {
        Optional<CabinRating> ratingDelete = reservationRateService.traerCalificacionReserva(id);
        if (ratingDelete.isPresent()) {
            reservationRateService.eliminarCalifiacion(id);
        }
        return "redirect:/formRatingReservation";
    }
}
