package org.example.idfpostgresql.repository;

import org.example.idfpostgresql.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {
    List<Entity> findByDbId(Long dbId);
    List<Entity> findByNameEnglishContainingIgnoreCase(String keyword);
    List<Entity> findByNameHebrewContaining(String keyword);
}