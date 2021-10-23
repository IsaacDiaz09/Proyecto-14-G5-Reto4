package com.usa.ciclo3.reto4.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.usa.ciclo3.reto4.model.Client;
import com.usa.ciclo3.reto4.repository.crud.ClientCrudRepository;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {


    @Autowired
    private ClientCrudRepository clienteCrudRepo;

    public List<Client> traerClientes(){
        return (List<Client>) clienteCrudRepo.findAll();
    }

    public Optional<Client> traerCliente(int id) {
        return clienteCrudRepo.findById(id);
    }

    public void guardarCliente(Client client) {
        clienteCrudRepo.save(client);
    }
}
