package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.Entity;
import org.example.idfpostgresql.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    // Get all entities
    public List<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    // Get entity by ID
    public Entity getEntityById(Integer id) {
        return entityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

    // Create a new entity
    public Entity createEntity(Entity entity) {
        return entityRepository.save(entity);
    }

    // Update entity
    public Entity updateEntity(Integer id, Entity entityDetails) {
        Entity entity = getEntityById(id);

        entity.setNameHebrew(entityDetails.getNameHebrew());
        entity.setNameEnglish(entityDetails.getNameEnglish());
        entity.setDbId(entityDetails.getDbId());

        return entityRepository.save(entity);
    }

    // Delete entity
    public void deleteEntity(Integer id) {
        Entity entity = getEntityById(id);
        entityRepository.delete(entity);
    }

    // Find entities by database ID
    public List<Entity> getEntitiesByDatabaseId(Long dbId) {
        return entityRepository.findByDbId(dbId);
    }

    // Search entities by English name
    public List<Entity> searchEntitiesByEnglishName(String keyword) {
        return entityRepository.findByNameEnglishContainingIgnoreCase(keyword);
    }

    // Search entities by Hebrew name
    public List<Entity> searchEntitiesByHebrewName(String keyword) {
        return entityRepository.findByNameHebrewContaining(keyword);
    }
}