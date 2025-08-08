package com.ezequielarce.notas.repository;

import com.ezequielarce.notas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNombreIgnoreCase(String nombre);
}
