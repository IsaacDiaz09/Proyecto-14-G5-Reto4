package com.usa.ciclo3.reto4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ciclo3.reto4.repository.CabanaRepository;
import com.usa.ciclo3.reto4.model.Cabana;

import java.util.Optional;
import java.util.Objects;
import java.util.List;

@Service
public class CabanaService {
    @Autowired
    CabanaRepository cabinRepo;

    public List<Cabana> traerTodo() {
        return cabinRepo.traerCabanas();
    }

    public void guardarCabana(Cabana cabin) {
        if (Objects.isNull(cabin.getId())) {
            cabinRepo.guardarCabana(cabin);
        } else {
            Optional<Cabana> cabinAux = cabinRepo.traerCabana(cabin.getId());
            if (!cabinAux.isPresent()) {
                cabinRepo.guardarCabana(cabin);
            }
            
        }
    }

}
