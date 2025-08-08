package com.ezequielarce.notas.controller;

import com.ezequielarce.notas.model.Category;
import com.ezequielarce.notas.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> listar() { return service.listarTodas(); }

    @PostMapping
    public Category crear(@RequestBody Category c) { return service.guardar(c); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
