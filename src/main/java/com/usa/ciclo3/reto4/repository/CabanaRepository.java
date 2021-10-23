package com.usa.ciclo3.reto4.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.usa.ciclo3.reto4.model.Cabana;
import com.usa.ciclo3.reto4.repository.crud.CabanaCrudRepository;
import java.util.List;
import java.util.Optional;

@Repository
public class CabanaRepository {

    @Autowired
    private CabanaCrudRepository cabinCrudRepo;

    public List<Cabana> traerCabanas(){
        return (List<Cabana>) cabinCrudRepo.findAll();
    }

    public Optional<Cabana> traerCabana(int id) {
        return cabinCrudRepo.findById(id);
    }

    public void guardarCabana(Cabana cabin) {
        cabinCrudRepo.save(cabin);
    }

}
