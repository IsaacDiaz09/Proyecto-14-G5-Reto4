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

    public Optional<Cabana> traerCabana(int id){
        return cabinRepo.traerCabana(id);
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

    public void actualizarCabana(Cabana cabin){
        if (!Objects.isNull(cabin.getId())){
            Optional<Cabana> cabinAux = cabinRepo.traerCabana(cabin.getId());
            
            if (cabinAux.isPresent()){
                Cabana cabinToUpdate = cabinAux.get();

                if (!Objects.isNull(cabin.getBrand())){
                    cabinToUpdate.setBrand(cabin.getBrand());
                }
                if (!Objects.isNull(cabin.getName())){
                    cabinToUpdate.setName(cabin.getName());
                }

                if (!Objects.isNull(cabin.getDescription())) {
                    cabinToUpdate.setDescription(cabin.getDescription());
                }

                if (!Objects.isNull(cabin.getRooms())){
                    cabinToUpdate.setRooms(cabin.getRooms());
                }
                cabinRepo.actualizaCabana(cabinToUpdate);
            }
        }
    }

    public void eliminarCabana(int id){
        if (!Objects.isNull(id)){
            Optional<Cabana> cabinAux = cabinRepo.traerCabana(id);
            if (cabinAux.isPresent()){
                cabinRepo.eliminarCabana(cabinAux.get());
            }
        }

    }

}
