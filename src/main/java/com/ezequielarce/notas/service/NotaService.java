package com.ezequielarce.notas.service;


import com.ezequielarce.notas.model.Nota;
import com.ezequielarce.notas.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.ezequielarce.notas.model.Category;
import com.ezequielarce.notas.repository.NotaRepository;
import com.ezequielarce.notas.repository.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotaService {
    private final NotaRepository notaRepository;
    private final CategoryRepository categoriaRepository;

    public NotaService(NotaRepository notaRepository, CategoryRepository categoriaRepository) {
        this.notaRepository = notaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Nota> obtenerTodas() {
        return notaRepository.findAll();
    }

    public Optional<Nota> obtenerPorId(Long id) {
        return notaRepository.findById(id);
    }

    public Nota guardar(Nota nota) {
        return notaRepository.save(nota);
    }

    public void eliminar(Long id) {
        notaRepository.deleteById(id);
    }

    public List<Nota> obtenerNotasPorArchivada(boolean archivada) {
        return notaRepository.findByArchivada(archivada);
    }

    public List<Nota> obtenerPorCategoriaId(Long categoriaId) {
        return notaRepository.findByCategoriasId(categoriaId);
    }

    public List<Nota> obtenerPorCategoriaNombre(String nombre) {
        return notaRepository.findByCategoriasNombreIgnoreCase(nombre);
    }

    @Transactional
    public Nota agregarCategoria(Long notaId, Long categoriaId) {
        Nota nota = notaRepository.findById(notaId).orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        Category categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        nota.addCategoria(categoria);
        return notaRepository.save(nota);
    }

    @Transactional
    public Nota removerCategoria(Long notaId, Long categoriaId) {
        Nota nota = notaRepository.findById(notaId).orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        Category categoria = categoriaRepository.findById(categoriaId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        nota.removeCategoria(categoria);
        return notaRepository.save(nota);
    }
}