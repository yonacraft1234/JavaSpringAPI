package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.Scrap;
import org.example.idfpostgresql.repository.ScrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScrapService {

    private final ScrapRepository scrapRepository;

    @Autowired
    public ScrapService(ScrapRepository scrapRepository) {
        this.scrapRepository = scrapRepository;
    }

    // Get all scraps
    public List<Scrap> getAllScraps() {
        return scrapRepository.findAll();
    }

    // Get scrap by ID
    public Scrap getScrapById(Long id) {
        return scrapRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scrap not found with id: " + id));
    }

    // Create a new scrap
    public Scrap createScrap(Scrap scrap) {
        if (scrap.getScrapDate() == null) {
            scrap.setScrapDate(LocalDate.now());
        }
        return scrapRepository.save(scrap);
    }

    // Update scrap
    public Scrap updateScrap(Long id, Scrap scrapDetails) {
        Scrap scrap = getScrapById(id);

        scrap.setDbId(scrapDetails.getDbId());
        scrap.setEntityId(scrapDetails.getEntityId());
        scrap.setFieldId(scrapDetails.getFieldId());
        scrap.setStatus(scrapDetails.getStatus());
        scrap.setUserId(scrapDetails.getUserId());

        if (scrapDetails.getScrapDate() != null) {
            scrap.setScrapDate(scrapDetails.getScrapDate());
        }

        return scrapRepository.save(scrap);
    }

    // Delete scrap
    public void deleteScrap(Long id) {
        Scrap scrap = getScrapById(id);
        scrapRepository.delete(scrap);
    }

    // Find scraps by database ID
    public List<Scrap> getScrapsByDbId(Integer dbId) {
        return scrapRepository.findByDbId(dbId);
    }

    // Find scraps by entity ID
    public List<Scrap> getScrapsByEntityId(Integer entityId) {
        return scrapRepository.findByEntityId(entityId);
    }

    // Find scraps by field ID
    public List<Scrap> getScrapsByFieldId(Integer fieldId) {
        return scrapRepository.findByFieldId(fieldId);
    }

    // Find scraps by status
    public List<Scrap> getScrapsByStatus(String status) {
        return scrapRepository.findByStatus(status);
    }

    // Find scraps by user ID
    public List<Scrap> getScrapsByUserId(Integer userId) {
        return scrapRepository.findByUserId(userId);
    }

    // Find scraps by date
    public List<Scrap> getScrapsByDate(LocalDate scrapDate) {
        return scrapRepository.findByScrapDate(scrapDate);
    }

    // Find scraps by date range
    public List<Scrap> getScrapsByDateRange(LocalDate startDate, LocalDate endDate) {
        return scrapRepository.findByScrapDateBetween(startDate, endDate);
    }
}