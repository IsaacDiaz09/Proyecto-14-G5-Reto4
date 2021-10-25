package com.usa.ciclo3.reto4.repository;

import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.repository.crud.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    ReservationCrudRepository reservationCrudRepo;

    public List<Reservation> traerReservas() {
        return (List<Reservation>) reservationCrudRepo.findAll();
    }

    public Optional<Reservation> traerReservacion(int id) {
        return reservationCrudRepo.findById(id);
    }

    public void guardarReservacion(Reservation reservation) {
        reservationCrudRepo.save(reservation);
    }

    public void actualizarReservacion(Reservation reservation){
        reservationCrudRepo.save(reservation);
    }

    public void eliminarReservacion(Reservation reservation){
        reservationCrudRepo.delete(reservation);
    }
 
}
