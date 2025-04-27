package org.example.idfpostgresql.repository;

import org.example.idfpostgresql.model.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UpdateRepository extends JpaRepository<Update, Long> {
    List<Update> findByDbId(Integer dbId);
    List<Update> findByEntityId(Integer entityId);
    List<Update> findByFieldId(Integer fieldId);
    List<Update> findByUserId(Integer userId);
    List<Update> findByUpdateDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}