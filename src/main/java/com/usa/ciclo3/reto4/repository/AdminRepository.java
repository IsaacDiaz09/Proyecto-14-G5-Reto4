package com.usa.ciclo3.reto4.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.usa.ciclo3.reto4.model.Admin;
import com.usa.ciclo3.reto4.repository.crud.AdminCrudRepository;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {

    @Autowired
    private AdminCrudRepository adminCrudRepo;
    
    @Transactional(readOnly=true)
    public List<Admin> traerAdmins(){
        return (List<Admin>) adminCrudRepo.findAll();
    }
    
    @Transactional(readOnly=true)
    public Optional<Admin> traerAdmin(int id) {
        return adminCrudRepo.findById(id);
    }
    
    @Transactional
    public void guardarAdmin(Admin admin) {
        adminCrudRepo.save(admin);
    }

    @Transactional
    public void actualizarAdmin(Admin admin){
        adminCrudRepo.save(admin);
    }

    @Transactional
    public void eliminarAdmin(Admin admin){
        adminCrudRepo.delete(admin);
    }

}
