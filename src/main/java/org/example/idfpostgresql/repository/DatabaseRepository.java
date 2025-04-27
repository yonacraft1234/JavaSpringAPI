package org.example.idfpostgresql.repository;

import org.example.idfpostgresql.model.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepository extends JpaRepository<Database, Long> {
    // Custom query methods
    List<Database> findByResponsibleUnit(String responsibleUnit);
    List<Database> findByResponsibleTeam(String responsibleTeam);
    List<Database> findByEnglishNameContainingIgnoreCase(String keyword);
}