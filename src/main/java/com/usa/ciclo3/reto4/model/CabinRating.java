package com.usa.ciclo3.reto4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Historia de usario 3.6: -Creación de Calificación de Reservas
 */

@Entity
@Table(name = "reservations_rating")
public class CabinRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

        @NotNull(message = "La calificación es requerida")
        @Max(value = 5, message = "El puntaje no puede ser mayor a 5")
        @Min(value = 1, message = "El puntaje no puede ser menor a 1")
	private int rate;

        @Size(min = 3, max = 250, message = "El nombre debe tener entre 250 y 3 caracteres")
	@Column(length = 250)
	private String message;

	@OneToOne
	@JoinColumn(name = "reservation_id")
	@JsonIgnoreProperties("score")
        @NotNull(message = "La calificación debe tener una reserva asociada")
	private Reservation reservation;

	public CabinRating(int rate, String message, Reservation reservation) {
		this.rate = rate;
		this.message = message;
		this.reservation = reservation;
	}

	public CabinRating() {

	}

	// --- Getters y Setters ---

	public int getId() {
		return id;
	}

	public void setId(int newId) {
		id = newId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
}