package org.example.idfpostgresql.repository;

import org.example.idfpostgresql.model.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findByDbId(Integer dbId);
    List<Scrap> findByEntityId(Integer entityId);
    List<Scrap> findByFieldId(Integer fieldId);
    List<Scrap> findByStatus(String status);
    List<Scrap> findByUserId(Integer userId);
    List<Scrap> findByScrapDate(LocalDate scrapDate);
    List<Scrap> findByScrapDateBetween(LocalDate startDate, LocalDate endDate);
}