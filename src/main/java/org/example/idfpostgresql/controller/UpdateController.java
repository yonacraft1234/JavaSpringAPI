package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.Update;
import org.example.idfpostgresql.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/updates")
@CrossOrigin(origins = "*")
public class UpdateController {

    private final UpdateService updateService;

    @Autowired
    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    // Get all updates
    @GetMapping
    public ResponseEntity<List<Update>> getAllUpdates() {
        List<Update> updates = updateService.getAllUpdates();
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // Get update by ID
    @GetMapping("/{id}")
    public ResponseEntity<Update> getUpdateById(@PathVariable Long id) {
        Update update = updateService.getUpdateById(id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    // Create a new update
    @PostMapping
    public ResponseEntity<Update> createUpdate(@RequestBody Update update) {
        Update newUpdate = updateService.createUpdate(update);
        return new ResponseEntity<>(newUpdate, HttpStatus.CREATED);
    }

    // Update an existing update
    @PutMapping("/{id}")
    public ResponseEntity<Update> updateUpdate(@PathVariable Long id, @RequestBody Update update) {
        Update updatedUpdate = updateService.updateUpdate(id, update);
        return new ResponseEntity<>(updatedUpdate, HttpStatus.OK);
    }

    // Delete update
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpdate(@PathVariable Long id) {
        updateService.deleteUpdate(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get updates by database ID
    @GetMapping("/database/{dbId}")
    public ResponseEntity<List<Update>> getUpdatesByDbId(@PathVariable Integer dbId) {
        List<Update> updates = updateService.getUpdatesByDbId(dbId);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // Get updates by entity ID
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<Update>> getUpdatesByEntityId(@PathVariable Integer entityId) {
        List<Update> updates = updateService.getUpdatesByEntityId(entityId);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // Get updates by field ID
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<Update>> getUpdatesByFieldId(@PathVariable Integer fieldId) {
        List<Update> updates = updateService.getUpdatesByFieldId(fieldId);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // Get updates by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Update>> getUpdatesByUserId(@PathVariable Integer userId) {
        List<Update> updates = updateService.getUpdatesByUserId(userId);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }

    // Get updates by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Update>> getUpdatesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Update> updates = updateService.getUpdatesByDateRange(startDate, endDate);
        return new ResponseEntity<>(updates, HttpStatus.OK);
    }
}