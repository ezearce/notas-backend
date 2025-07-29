package com.ezequielarce.notas.controller;

import com.ezequielarce.notas.model.Nota;
import com.ezequielarce.notas.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public List<Nota> obtenerTodas() {
        System.out.println("📌 Se está llamando al endpoint GET /api/notas");
        return notaService.obtenerTodas();
    }

    @PostMapping
    public Nota crear(@RequestBody Nota nota) {
        return notaService.guardar(nota);
    }

    @GetMapping("/{id}")
    public Nota obtenerPorId(@PathVariable Long id) {
        return notaService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        notaService.eliminar(id);
    }

    @PutMapping("/{id}")
    public Nota actualizar(@PathVariable Long id, @RequestBody Nota notaActualizada) {
        return notaService.obtenerPorId(id)
                .map(notaExistente -> {
                    notaExistente.setTitulo(notaActualizada.getTitulo());
                    notaExistente.setContenido(notaActualizada.getContenido());
                    notaExistente.setArchivada(notaActualizada.isArchivada());
                    return notaService.guardar(notaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    @GetMapping("/activas")
    public List<Nota> obtenerNotasActivas() {
        return notaService.obtenerNotasPorArchivada(false);
    }

    @GetMapping("/archivadas")
    public List<Nota> obtenerNotasArchivadas() {
        return notaService.obtenerNotasPorArchivada(true);
    }

}
