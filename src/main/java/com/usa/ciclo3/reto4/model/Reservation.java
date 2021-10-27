package com.usa.ciclo3.reto4.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Historia de usuario 3.5: creacion de reservas
 */
@Entity
@Table(name = "reservations")
public class Reservation implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3291318410135834648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;

	// implementar validacion sobre que sea la fecha actual
	private LocalDate startDate;

	// @Future(message = "La fecha de entrega debe ser posterior a la actual")
	@NotNull(message = "La fecha de entrega es requerida")
	private LocalDate devolutionDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "clientCabins")
	@JsonIgnoreProperties({ "reservations", "client" })
	private Cabana cabin;

	@ManyToOne
	@JoinColumn(name = "clientReservations")
	@JsonIgnoreProperties({ "reservations", "messages" })
	private Client client;

	// Relacion 1 - 1
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "reservation")
	@JsonIgnoreProperties("reservation")
	private CabinRating score;

	// Constructores

	public Reservation() {

	}

	public Reservation(LocalDate startDate, LocalDate devolutionDate, String status, Cabana cabin, Client client,
			CabinRating score) {
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.status = status;
		this.cabin = cabin;
		this.client = client;
		this.score = score;
	}

	// --- Getters y Setters ---

	public CabinRating getScore() {
		return score;
	}

	public void setScore(CabinRating score) {
		this.score = score;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Si no recibe la feca de inicio de la reseva, la establece como la fecha
	 * actual tomada del sistema
	 * 
	 * @param startDate
	 */
	public void setStartDate(LocalDate startDate) {
		if (startDate == null) {
			this.startDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} else {
			this.startDate = startDate;
		}
	}

	public LocalDate getDevolutionDate() {
		return this.devolutionDate;
	}

	public void setDevolutionDate(LocalDate devolutionDate) {
		this.devolutionDate = devolutionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cabana getCabin() {
		return cabin;
	}

	public void setCabin(Cabana cabin) {
		this.cabin = cabin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
