package com.usa.ciclo3.reto4.repository;

import com.usa.ciclo3.reto4.repository.crud.CategoryCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.usa.ciclo3.reto4.model.Category;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    @Autowired
    CategoryCrudRepository categoryCrudRepo;

    /**
     * trae todas las categorias
     * @return List<Category>
     */
    @Transactional(readOnly=true)
    public List<Category> traerCategorias(){
        return (List<Category>) categoryCrudRepo.findAll();
    }

    @Transactional(readOnly=true)
    public Optional <Category> traerCategoria(int id){
        return categoryCrudRepo.findById(id);
    }

    @Transactional
    public void guardarCategoria(Category cat){
        categoryCrudRepo.save(cat);
    }

    @Transactional
    public void actualizarCategoria(Category cat){
        categoryCrudRepo.save(cat);
    }

    @Transactional
    public void eliminarCategoria(Category cat){
        categoryCrudRepo.delete(cat);
    }
}
