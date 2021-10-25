package com.usa.ciclo3.reto4.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.usa.ciclo3.reto4.model.CabinRating;
import com.usa.ciclo3.reto4.repository.crud.CabinRatingCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CabinRatingRepository {
    @Autowired
    CabinRatingCrudRepository cabinRatingCrudRepo;

    @Transactional(readOnly=true)
    public List<CabinRating> traerTodas() {
        return (List<CabinRating>) cabinRatingCrudRepo.findAll();
    }
    @Transactional(readOnly=true)
    public Optional<CabinRating> traerCalificacion(int id) {
        return cabinRatingCrudRepo.findById(id);
    }
    @Transactional
    public void guardarCalificacion(CabinRating cabinRating) {
        cabinRatingCrudRepo.save(cabinRating);
    }

    @Transactional
    public void actualizarCalificacion(CabinRating cabinRating){
        cabinRatingCrudRepo.save(cabinRating);
    }

    @Transactional
    public void eliminarCalificacion(CabinRating cabinRating){
        cabinRatingCrudRepo.delete(cabinRating);
    }
    
}
