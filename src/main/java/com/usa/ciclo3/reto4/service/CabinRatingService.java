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

}
