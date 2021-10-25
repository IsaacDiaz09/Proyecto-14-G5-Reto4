package com.usa.ciclo3.reto4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ciclo3.reto4.repository.CabinRatingRepository;
import com.usa.ciclo3.reto4.model.CabinRating;

import java.util.Optional;
import java.util.Objects;
import java.util.List;

@Service
public class CabinRatingService {
    @Autowired
    CabinRatingRepository cabinRatingRepository;

    public List<CabinRating> TraerTodo() {
        return cabinRatingRepository.traerTodas();
    }

    /**
     * comprueba que el objeto recibido no exista, entonces realiza la persistencia
     * 
     * @param rating
     */
    public void guardarCalificacion(CabinRating rating) {
        if (Objects.isNull(rating.getId())) {
            cabinRatingRepository.guardarCalificacion(rating);

        } else {
            Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(rating.getId());
            if (!ratingAux.isPresent()) {
                cabinRatingRepository.guardarCalificacion(rating);

            }
        }
    }

    /**
     * actualiza una calificacion con un nuevo mensaje y puntuacion si son enviados
     * y dicha calificacion existe
     * 
     * @param rating
     */
    public void actualizarCalificacion(CabinRating rating) {
        if (!Objects.isNull(rating.getId())) {
            Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(rating.getId());
            if (ratingAux.isPresent()) {
                CabinRating ratingToUpdate = ratingAux.get();

                if (!Objects.isNull(rating.getRate())) {
                    ratingToUpdate.setRate(rating.getRate());
                }

                if (!Objects.isNull(rating.getMessage())) {
                    ratingToUpdate.setMessage(rating.getMessage());
                }
                cabinRatingRepository.actualizarCalificacion(ratingToUpdate);
            }
        }
    }

    /**
     * metodo para eliminar una calificacion de cabaña si existe

     * @param id
     */
    public void eliminarCalifiacion(int id) {
        if (!Objects.isNull(id)) {
            Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(id);
            if (ratingAux.isPresent()) {
                cabinRatingRepository.eliminarCalificacion(ratingAux.get());
            }
        }
    }
}
