package com.ezequielarce.notas.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    // Evitar recursion JSON al serializar notas -> categorias -> notas
    @JsonIgnoreProperties("categorias")
    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private Set<Nota> notas = new HashSet<>();

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Nota> getNotas() { return notas; }
    public void setNotas(Set<Nota> notas) { this.notas = notas; }
}
