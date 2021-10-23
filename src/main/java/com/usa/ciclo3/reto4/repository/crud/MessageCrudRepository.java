package com.usa.ciclo3.reto4.repository.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.usa.ciclo3.reto4.model.Message;

@Repository
public interface MessageCrudRepository extends CrudRepository <Message,Integer>{
    
}
