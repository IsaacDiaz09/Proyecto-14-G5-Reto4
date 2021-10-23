package com.usa.ciclo3.reto4.repository.crud;

import com.usa.ciclo3.reto4.model.Cabana;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabanaCrudRepository extends CrudRepository<Cabana, Integer> {

}