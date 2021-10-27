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
    // Se inyecta el repositorio
    @Autowired
    ClientRepository clientRepo;

    /**
     * Devuelve a todos los clientes
     * @return List Client
     */
    public List<Client> TraerTodo() {
        return clientRepo.traerClientes();
    }

    public Optional<Client> traerCliente(int id) {
        return clientRepo.traerCliente(id);
    }

    public void actualizarClienteForm(Client client){
        clientRepo.actualizaCliente(client);
    }

    /**
     * Valiza que el id no sea nulo y que no existe, entonces persiste el obj
     * @param Client
     */
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

    /**
     * Actualiza los atributos nombre, edad, contraseña de un cliente si este existe y no son nulos
     * @param client
     */
    public void actualizaCliente(Client client){
        if (!Objects.isNull(client.getIdClient())){
            Optional<Client> clientAux = clientRepo.traerCliente(client.getIdClient());

            if (clientAux.isPresent()){
                Client clientToUpdate = clientAux.get();

                if (!Objects.isNull(client.getName())){
                    clientToUpdate.setName(client.getName());
                }
                if (!Objects.isNull(client.getAge())){
                    clientToUpdate.setAge(client.getAge());
                }
                if (!Objects.isNull(client.getName())){
                    clientToUpdate.setPassword(client.getPassword());
                }

                clientRepo.actualizaCliente(clientToUpdate);
            }
        }
    }

    /**
     * Elimina un cliente si este existe
     * @param id
     */
    public void eliminarCliente(int id){
        if (!Objects.isNull(id)){
            Optional<Client> clienteAux = clientRepo.traerCliente(id);
            if(clienteAux.isPresent()){
                clientRepo.eliminarCliente(clienteAux.get());
            }
        }
    }

}
