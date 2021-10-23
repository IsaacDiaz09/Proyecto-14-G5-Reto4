package com.usa.ciclo3.reto4.service;

import com.usa.ciclo3.reto4.model.Message;
import com.usa.ciclo3.reto4.repository.MessageRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository msgRepo;

    public List<Message> TraerTodo() {
        return msgRepo.traerMensajes();
    }

    public void guardarMensaje(Message msg) {
        if (Objects.isNull(msg.getIdMessage())) {
            msgRepo.guardarMensaje(msg);
        } else {
            Optional<Message> catAux = msgRepo.traerMensaje(msg.getIdMessage());
            if (!catAux.isPresent()) {
                msgRepo.guardarMensaje(msg);

            }
        }
    }

}
