package com.usa.ciclo3.reto4.controller.forms_controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.usa.ciclo3.reto4.model.CabinRating;
import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.service.CabinRatingService;
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
@RequestMapping("formReservation")
public class RatingFormController {
	int idReservation;
	@Autowired
	private CabinRatingService reservationRateService;

	@Autowired
	private ReservationService reservationService;


	/**
	 * Cuando se solicita calificar una cabaña se llama este metodo y retorna el
	 * formulario
	 */
	@GetMapping("/rate/{id}")
	public String redireccionaCalificarReserva(@PathVariable("id") int id, Model modelo) {
		Optional<Reservation> reservationAux = reservationService.traerReserva(id);
		if (reservationAux.isPresent()){
			setId(id);
			int points = isRated(getId());
			modelo.addAttribute("rating", new CabinRating());
			modelo.addAttribute("points", points);
			return "formRatingReservation";
		}

		return "redirect:/formReservation";

	}

	@PostMapping("/rate/save")
	public String guardarCalificacion(@Valid CabinRating cabinRating, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "formRatingReservation";
		}
		Reservation reservation = reservationService.traerReserva(getId()).get();
		cabinRating.setReservation(reservation);
		reservationRateService.guardarCalificacion(cabinRating);
		return "redirect:/formReservation";
	}
	
	// Si llegase a recibir null porque no hay calificacion, explotta,entonces, explicitamente hay que decirle que no haga nada 
	@GetMapping("/rate/update/null")
	public String formEditNullRating(){
		return "redirect:/formReservation";
	}
    
	@GetMapping("/rate/update/{id}")
	public String formEditRating(@PathVariable("id") Integer id, Model modelo){
		Optional<CabinRating> rating = reservationRateService.traerCalificacionReserva(id);

		if (rating.isPresent()){
			modelo.addAttribute("revervationRating",rating.get());
			return "updateRatingForm";
		}

		return "redirect:/formReservation";

	}


	@PostMapping("/rate/update/save")
	public String editarCalificacion(@Valid CabinRating cabinRating, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "updateRatingForm";
		}

		reservationRateService.actualizarCalificacion(cabinRating);

		return "redirect:/formReservation";
	}

	private void setId(int id) {
		idReservation = id;
	}

	private int getId() {
		return idReservation;
	}

	// Valida si una reserva ya ha sido calificada o no
	private int isRated(int id){
		List<CabinRating> ratings = reservationRateService.TraerTodo();

		for (CabinRating r: ratings){
			if (r.getReservation().getIdReservation() == id){
				return 0;
			}
		}
		return -1;

	}
}
