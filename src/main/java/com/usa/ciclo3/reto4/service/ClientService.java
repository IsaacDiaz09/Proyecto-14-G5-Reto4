package com.usa.ciclo3.reto4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ciclo3.reto4.repository.ClientRepository;
import com.usa.ciclo3.reto4.model.Client;

import java.util.Optional;
import java.util.Objects;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepo;

    public List<Client> TraerTodo() {
        return clientRepo.traerClientes();
    }

    public void guardarCliente(Client client) {
        if (Objects.isNull(client.getIdClient())) {
            if (client.getAge() > 0) {
                clientRepo.guardarCliente(client);
            }
        } else {
            Optional<Client> catAux = clientRepo.traerCliente(client.getIdClient());
            if (!catAux.isPresent()) {
                clientRepo.guardarCliente(client);
            }
        }

    }

}
