package com.ezequielarce.notas.service;

import com.ezequielarce.notas.model.Category;
import com.ezequielarce.notas.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> listarTodas() { return repo.findAll(); }

    public Optional<Category> buscarPorId(Long id) { return repo.findById(id); }

    public Optional<Category> buscarPorNombre(String nombre) { return repo.findByNombreIgnoreCase(nombre); }

    public Category guardar(Category c) { return repo.save(c); }

    public void eliminar(Long id) { repo.deleteById(id); }
}
