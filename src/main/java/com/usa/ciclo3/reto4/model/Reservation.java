package com.usa.ciclo3.reto4.model;

import java.io.Serializable;
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

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	
	@DateTimeFormat(iso=ISO.DATE)
	private Date startDate;

	@NotNull(message = "La fecha de entrega es requerida")
	@DateTimeFormat(iso=ISO.DATE)
	private Date devolutionDate;

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
	@OneToOne(mappedBy = "reservation")
	@JsonIgnoreProperties("reservation")
	private CabinRating score;

	// Constructores

	public Reservation() {

	}

	public Reservation(Date startDate, Date devolutionDate, String status, Cabana cabin, Client client,
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

	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Si no recibe la feca de inicio de la reseva, la establece como la fecha
	 * actual tomada del sistema
	 * 
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		if (startDate == null) {
			this.startDate = new Date();
		} else {
			this.startDate = startDate;
		}
	}

	public Date getDevolutionDate() {
		return this.devolutionDate;
	}

	public void setDevolutionDate(Date devolutionDate) {
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
