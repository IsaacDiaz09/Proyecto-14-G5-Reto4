package com.usa.ciclo3.reto4.controller.rest_controller;

import com.usa.ciclo3.reto4.model.Admin;
import com.usa.ciclo3.reto4.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

/**
 * Listar todos los admins -> (endpoint: /api/Admin/all)
 * Persistir entidad adminitrador -> (endpoint: /api/Admin/save) 
 * Editar algun administrador -> (endpoint: /api/Admin/update) 
*/
@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class AdminRestController {

    // Inyeccion de dependencia
    @Autowired
    AdminService adminService;

    /**
     * Retorna todos los administradores
     * @return List Admin
     */
    @GetMapping("/all")
    public List<Admin> traerAdmins() {
        return adminService.traerTodo();
    }

    /**
     * Persiste un objeto que va en el cuerpo de la peticion
     * @param admin
     */
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void guardarAdmin(@RequestBody Admin admin) {
        adminService.guardarAdmin(admin);
    }

    /**
     * Actualiza un administrador con los atributos pasados en el JSON
     * @param admin
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void actualizarAdmin(@RequestBody Admin admin){
        adminService.actualizarAdmin(admin);
    }

    /**
     * Elimina un administrador recibiendo el ID por la Url si existe
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarAdmin(@PathVariable("id") int id){
        adminService.eliminarAdmin(id);
    }

}
