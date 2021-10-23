package com.usa.ciclo3.reto4.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

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

    private String startDate;

    @NotEmpty(message="La fecha de entrega es requerida")
    @Future(message="La fecha de entrega debe ser posterior a la actual")
    private String devolutionDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "clientCabins")
    @JsonIgnoreProperties({ "reservations", "client" })
    private Cabana cabin;

    @ManyToOne
    @JoinColumn(name = "clientReservations")
    @JsonIgnoreProperties({ "reservations", "messages"})
    private Client client;

    // Relacion 1 - 1
    @OneToOne(cascade = CascadeType.PERSIST,mappedBy="reservation")
    @JsonIgnoreProperties("reservation")
    private CabinRating score;

    // Constructores

    public Reservation() {

    }

    public Reservation(String startDate, String devolutionDate, String status, Cabana cabin, Client client,
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        if (startDate == null || startDate.trim().equals("")) {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "T00:00:00.000+00:00";
        } else {
            this.startDate = startDate + "T00:00:00.000+00:00";
        }
    }

    public String getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(String devolutionDate) {
        if (devolutionDate == null || devolutionDate.trim().equals("")) {
            this.devolutionDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "T00:00:00.000+00:00";
        } else {
            this.devolutionDate = devolutionDate + "T00:00:00.000+00:00";
        }
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
