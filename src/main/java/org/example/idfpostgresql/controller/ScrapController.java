package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.Scrap;
import org.example.idfpostgresql.service.ScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scraps")
@CrossOrigin(origins = "*")
public class ScrapController {

    private final ScrapService scrapService;

    @Autowired
    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    // Get all scraps
    @GetMapping
    public ResponseEntity<List<Scrap>> getAllScraps() {
        List<Scrap> scraps = scrapService.getAllScraps();
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scrap by ID
    @GetMapping("/{id}")
    public ResponseEntity<Scrap> getScrapById(@PathVariable Long id) {
        Scrap scrap = scrapService.getScrapById(id);
        return new ResponseEntity<>(scrap, HttpStatus.OK);
    }

    // Create a new scrap
    @PostMapping
    public ResponseEntity<Scrap> createScrap(@RequestBody Scrap scrap) {
        Scrap newScrap = scrapService.createScrap(scrap);
        return new ResponseEntity<>(newScrap, HttpStatus.CREATED);
    }

    // Update scrap
    @PutMapping("/{id}")
    public ResponseEntity<Scrap> updateScrap(@PathVariable Long id, @RequestBody Scrap scrap) {
        Scrap updatedScrap = scrapService.updateScrap(id, scrap);
        return new ResponseEntity<>(updatedScrap, HttpStatus.OK);
    }

    // Delete scrap
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScrap(@PathVariable Long id) {
        scrapService.deleteScrap(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get scraps by database ID
    @GetMapping("/database/{dbId}")
    public ResponseEntity<List<Scrap>> getScrapsByDbId(@PathVariable Integer dbId) {
        List<Scrap> scraps = scrapService.getScrapsByDbId(dbId);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by entity ID
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<Scrap>> getScrapsByEntityId(@PathVariable Integer entityId) {
        List<Scrap> scraps = scrapService.getScrapsByEntityId(entityId);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by field ID
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<Scrap>> getScrapsByFieldId(@PathVariable Integer fieldId) {
        List<Scrap> scraps = scrapService.getScrapsByFieldId(fieldId);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Scrap>> getScrapsByStatus(@PathVariable String status) {
        List<Scrap> scraps = scrapService.getScrapsByStatus(status);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Scrap>> getScrapsByUserId(@PathVariable Integer userId) {
        List<Scrap> scraps = scrapService.getScrapsByUserId(userId);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Scrap>> getScrapsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Scrap> scraps = scrapService.getScrapsByDate(date);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }

    // Get scraps by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Scrap>> getScrapsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Scrap> scraps = scrapService.getScrapsByDateRange(startDate, endDate);
        return new ResponseEntity<>(scraps, HttpStatus.OK);
    }
}