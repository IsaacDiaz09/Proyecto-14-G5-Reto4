package com.usa.ciclo3.reto4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.ciclo3.reto4.repository.CategoryRepository;
import com.usa.ciclo3.reto4.model.Category;

import java.util.Optional;
import java.util.Objects;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepo;

    public List<Category> TraerTodo() {
        return categoryRepo.traerCategorias();
    }

    public void guardarCategoria(Category cat) {
        if (Objects.isNull(cat.getId())) {
            categoryRepo.guardarCategoria(cat);
        } else {
            Optional<Category> catAux = categoryRepo.traerCategoria(cat.getId());
            if (!catAux.isPresent()) {
                categoryRepo.guardarCategoria(cat);
            }
        }
    }

    /**
     * Actualiza los atributos de una categoria que no sean nulos, solo si la categoria existe
     * @param cat
     */
    public void actualizarCategoria(Category cat) {
        if (!Objects.isNull(cat.getId())) {
            Optional<Category> catAux = categoryRepo.traerCategoria(cat.getId());
        
            if (catAux.isPresent()) {
                Category category = catAux.get();
 
                if (!Objects.isNull(cat.getName())) {
                    category.setName(cat.getName());
                }
                if (!Objects.isNull(cat.getDescription())) {
                    category.setDescription(cat.getDescription());
                }

                categoryRepo.actualizarCategoria(category);
            }
        }
    }

    public void eliminarCategoria(int id) {
        if (!Objects.isNull(id)) {
            Optional<Category> catAux = categoryRepo.traerCategoria(id);
            if (catAux.isPresent()) {
                categoryRepo.eliminarCategoria(catAux.get());
            }
        }
    }

}
