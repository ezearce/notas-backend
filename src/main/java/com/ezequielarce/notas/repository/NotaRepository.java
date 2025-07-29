package com.ezequielarce.notas.repository;

import com.ezequielarce.notas.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {

}
