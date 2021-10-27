package com.usa.ciclo3.reto4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ciclo3.reto4.repository.AdminRepository;
import com.usa.ciclo3.reto4.model.Admin;

import java.util.Optional;
import java.util.Objects;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public List<Admin> traerTodo() {
        return adminRepo.traerAdmins();
    }
    
    public Optional<Admin> traerAdmin(int id) {
    	return adminRepo.traerAdmin(id);
    }

    public Admin buscar(Admin admin){
        return adminRepo.traerAdmin(admin.getId()).orElse(null);
    }

    /**
     * Realiza las validaciones y si cumple guarda el administrador
     * @param admin
     */
    public void guardarAdmin(Admin admin) {
        if (Objects.isNull(admin.getId())) {
            adminRepo.guardarAdmin(admin);
        } else {
            Optional<Admin> adminAux = adminRepo.traerAdmin(admin.getId());
            if (!adminAux.isPresent()) {
                adminRepo.guardarAdmin(admin);
            }
        }
    }

    public void actualizarAdminForm(Admin admin){
        adminRepo.actualizarAdmin(admin);
    }

    /**
     * Actualiza un administrador recibido en una peticion put, actualiza solo
     * los campos que se hayan enviado en el JSON, lo demas lo deja intacto
     * @param admin
     */
    public void actualizarAdmin(Admin admin){
        if(!Objects.isNull(admin.getId())){
            Optional<Admin> adminAux = adminRepo.traerAdmin(admin.getId());
            if (adminAux.isPresent()){
                Admin admn = adminAux.get();
                
                if (!Objects.isNull(admn.getName())){
                    admn.setName(admn.getName());
                }
                if (!Objects.isNull(admn.getEmail())){
                    admn.setEmail(admn.getEmail());
                }
                if (!Objects.isNull(admn.getPassword())){
                    admn.setPassword(admn.getPassword());
                }

                adminRepo.guardarAdmin(admn);
                
            }
        }
    }

    /**
     * Elimina el objeto solo si no es nulo y si existe
     * @param int id
     */
    public void eliminarAdmin(int id){
        if (!Objects.isNull(id)){
            Optional<Admin> adminAux = adminRepo.traerAdmin(id);
            if (adminAux.isPresent()){
                adminRepo.eliminarAdmin(adminAux.get());
            }
        }
    }
}
