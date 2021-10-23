package com.usa.ciclo3.reto4.repository.crud;

import com.usa.ciclo3.reto4.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCrudRepository extends CrudRepository <Category,Integer> {
    
}
