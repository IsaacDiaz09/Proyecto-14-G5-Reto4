package com.usa.ciclo3.reto4.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Historia de usuario 3.3, entidad clientes
 */

@Entity
@Table(name="clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idClient;


    @NotEmpty(message="El email es obligatorio")
    @Size(max=45,message="El email es muy largo")
    @Column(length=45)
    private String email;

    @Size(min=8,max=45,message="La contraseña debe tener entre 8 y 45 caracteres")
    @NotEmpty(message="La contraseña es obligatoria")
    @Column(length=45)
    private String password;
    
    @Size(min=3,max=250, message = "El nombre debe tener maximo 250 caracteres")
    @Column(length=250)
    private String name;

    @Positive(message="La edad no puede ser negativa")
    private int age;

    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "client")
    @JsonIgnoreProperties("client")
    private List<Message> messages;

    
    @OneToMany(cascade = {CascadeType.PERSIST},mappedBy = "client")
    @JsonIgnoreProperties("cabin")
    private List<Reservation> reservations;

    // Constructores

    public Client(){

    }

    public Client( String email, String password, String name, int age, List<Message> messages,
            List<Reservation> reservations) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.messages = messages;
        this.reservations = reservations;
    }

    // --- Getters y Setters ---

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    
}