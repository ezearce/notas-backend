package com.ezequielarce.notas.repository;

import com.ezequielarce.notas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByArchivada(boolean archivada);
    List<Nota> findByCategoriasId(Long categoriaId);
    List<Nota> findByCategoriasNombreIgnoreCase(String nombre);
}
