package com.usa.ciclo3.reto4.repository;

import com.usa.ciclo3.reto4.model.Message;
import com.usa.ciclo3.reto4.repository.crud.MessageCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    @Autowired
    MessageCrudRepository messageCrudRepo;

    public List<Message> traerMensajes() {
        return (List<Message>) messageCrudRepo.findAll();
    }

    public Optional<Message> traerMensaje(int id) {
        return messageCrudRepo.findById(id);
    }

    public void guardarMensaje(Message msg) {
        messageCrudRepo.save(msg);
    }

    public void actualizaMensaje(Message msg){
        messageCrudRepo.save(msg);
    }

    public void eliminarMensaje(Message msg){
        messageCrudRepo.delete(msg);
    }
}
