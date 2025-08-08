package com.ezequielarce.notas.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private boolean archivada = false;

    private Instant creadoEn = Instant.now();

    @JsonIgnoreProperties("notas") // evita serialización infinita
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "nota_categoria",
        joinColumns = @JoinColumn(name = "nota_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    
    private Set<Category> categorias = new HashSet<>();

    // opcional: helpers para añadir/quitar
    public void addCategoria(Category c) {
        this.categorias.add(c);
        c.getNotas().add(this);
    }
    public void removeCategoria(Category c) {
        this.categorias.remove(c);
        c.getNotas().remove(this);
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isArchivada() {
        return archivada;
    }

    public void setArchivada(boolean archivada) {
        this.archivada = archivada;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Set<Category> getCategorias() { 
        return categorias; 
    
    }
    public void setCategorias(Set<Category> categorias) { 
        this.categorias = categorias; 
    }
}
