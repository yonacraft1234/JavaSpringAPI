package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.Entity;
import org.example.idfpostgresql.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
@CrossOrigin(origins = "*")
public class EntityController {

    private final EntityService entityService;

    @Autowired
    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    // Get all entities
    @GetMapping
    public ResponseEntity<List<Entity>> getAllEntities() {
        List<Entity> entities = entityService.getAllEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    // Get entity by ID
    @GetMapping("/{id}")
    public ResponseEntity<Entity> getEntityById(@PathVariable Integer id) {
        Entity entity = entityService.getEntityById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    // Create a new entity
    @PostMapping
    public ResponseEntity<Entity> createEntity(@RequestBody Entity entity) {
        Entity newEntity = entityService.createEntity(entity);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    // Update entity
    @PutMapping("/{id}")
    public ResponseEntity<Entity> updateEntity(@PathVariable Integer id, @RequestBody Entity entity) {
        Entity updatedEntity = entityService.updateEntity(id, entity);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    // Delete entity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Integer id) {
        entityService.deleteEntity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get entities by database ID
    @GetMapping("/database/{dbId}")
    public ResponseEntity<List<Entity>> getEntitiesByDatabaseId(@PathVariable Long dbId) {
        List<Entity> entities = entityService.getEntitiesByDatabaseId(dbId);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    // Search entities by English name
    @GetMapping("/search/english")
    public ResponseEntity<List<Entity>> searchEntitiesByEnglishName(@RequestParam String keyword) {
        List<Entity> entities = entityService.searchEntitiesByEnglishName(keyword);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    // Search entities by Hebrew name
    @GetMapping("/search/hebrew")
    public ResponseEntity<List<Entity>> searchEntitiesByHebrewName(@RequestParam String keyword) {
        List<Entity> entities = entityService.searchEntitiesByHebrewName(keyword);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}