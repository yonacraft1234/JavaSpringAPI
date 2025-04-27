package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.Update;
import org.example.idfpostgresql.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpdateService {

    private final UpdateRepository updateRepository;

    @Autowired
    public UpdateService(UpdateRepository updateRepository) {
        this.updateRepository = updateRepository;
    }

    // Get all updates
    public List<Update> getAllUpdates() {
        return updateRepository.findAll();
    }

    // Get update by ID
    public Update getUpdateById(Long id) {
        return updateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found with id: " + id));
    }

    // Create a new update
    public Update createUpdate(Update update) {
        if (update.getUpdateDate() == null) {
            update.setUpdateDate(LocalDateTime.now());
        }
        return updateRepository.save(update);
    }

    // Update an existing update
    public Update updateUpdate(Long id, Update updateDetails) {
        Update update = getUpdateById(id);

        update.setDbId(updateDetails.getDbId());
        update.setEntityId(updateDetails.getEntityId());
        update.setFieldId(updateDetails.getFieldId());
        update.setOldValue(updateDetails.getOldValue());
        update.setNewValue(updateDetails.getNewValue());
        update.setUserId(updateDetails.getUserId());

        if (updateDetails.getUpdateDate() != null) {
            update.setUpdateDate(updateDetails.getUpdateDate());
        }

        return updateRepository.save(update);
    }

    // Delete update
    public void deleteUpdate(Long id) {
        Update update = getUpdateById(id);
        updateRepository.delete(update);
    }

    // Find updates by database ID
    public List<Update> getUpdatesByDbId(Integer dbId) {
        return updateRepository.findByDbId(dbId);
    }

    // Find updates by entity ID
    public List<Update> getUpdatesByEntityId(Integer entityId) {
        return updateRepository.findByEntityId(entityId);
    }

    // Find updates by field ID
    public List<Update> getUpdatesByFieldId(Integer fieldId) {
        return updateRepository.findByFieldId(fieldId);
    }

    // Find updates by user ID
    public List<Update> getUpdatesByUserId(Integer userId) {
        return updateRepository.findByUserId(userId);
    }

    // Find updates by date range
    public List<Update> getUpdatesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return updateRepository.findByUpdateDateBetween(startDate, endDate);
    }
}