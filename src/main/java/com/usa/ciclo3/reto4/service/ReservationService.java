package com.usa.ciclo3.reto4.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.usa.ciclo3.reto4.model.Reservation;
import com.usa.ciclo3.reto4.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    // se inyecta la dependencia del servicio
    @Autowired
    ReservationRepository reservationRepo;

    /**
     * regresa todas las reservas
     * 
     * @return List Reservation
     */
    public List<Reservation> traerReservas() {
        return reservationRepo.traerReservas();
    }

    /**
     * trae una reserva dentro de un obj Optional
     * 
     * @param id
     * @return Optional<Reservation>
     */
    public Optional<Reservation> traerReserva(int id) {
        return reservationRepo.traerReservacion(id);
    }

    /**
     * persiste un objeto de tipo reserva si este ya no existe
     * 
     * @param reservation
     */
    public void guardarReservacion(Reservation reservation) {
        if (Objects.isNull(reservation.getIdReservation())) {
            if (reservation.getStartDate() == null) {
                reservation.setStartDate(new Date());
            }
            reservation.setStatus("created");
            reservationRepo.guardarReservacion(reservation);
        } else {
            Optional<Reservation> reservationAux = reservationRepo.traerReservacion(reservation.getIdReservation());
            if (!reservationAux.isPresent()) {
                if (reservation.getStartDate() == null) {
                    reservation.setStartDate(new Date());
                }
                reservation.setStatus("created");
                reservationRepo.guardarReservacion(reservation);

            }
        }
    }

    /**
     * actualiza un objeto de tipo reserva con nuevos atributos
     * 
     * @param reservation
     */
    public void actualizaReserva(Reservation reservation) {
        if (!Objects.isNull(reservation.getIdReservation())) {
            Optional<Reservation> reservationAux = reservationRepo.traerReservacion(reservation.getIdReservation());
            if (reservationAux.isPresent()) {
                Reservation reservationToUpdate = reservationAux.get();

                if (!Objects.isNull(reservation.getStartDate())) {
                    reservationToUpdate.setStartDate(reservation.getStartDate());
                }
                
                if (!Objects.isNull(reservation.getDevolutionDate())) {
                    reservationToUpdate.setDevolutionDate(reservation.getDevolutionDate());
                }

                if (!Objects.isNull(reservation.getStatus())) {
                    reservationToUpdate.setStatus(reservation.getStatus());
                }

                // valida que la fecha de inicio sea anterior a la de entrega
                if (reservation.getStartDate().before(reservation.getDevolutionDate())) {
                    reservationRepo.actualizarReservacion(reservationToUpdate);
                }

            }
        }
    }

    /**
     * elimina una reserva si, y solo si esta existe
     * 
     * @param id
     */
    public void eliminarReservacion(int id) {
        if (!Objects.isNull(id)) {
            Optional<Reservation> reservationAux = reservationRepo.traerReservacion(id);
            if (reservationAux.isPresent()) {
                reservationRepo.eliminarReservacion(reservationAux.get());
            }
        }
    }

    /**
     * valida que la fecha de inicio sea anterior a la fecha de devolucion
     * 
     * @param startDate
     * @param devolutionDate
     * @return boolean
     */
    public boolean validaFechaInicio(LocalDate startDate, LocalDate devolutionDate) {
        return startDate.isBefore(devolutionDate);
    }
}
