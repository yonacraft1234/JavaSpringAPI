package org.example.idfpostgresql.repository;

import org.example.idfpostgresql.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {
    List<Field> findByEntityId(Long entityId);
    List<Field> findByTypeOf(String typeOf);
    List<Field> findByNameEnglishContainingIgnoreCase(String keyword);
}