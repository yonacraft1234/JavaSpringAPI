package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.Database;
import org.example.idfpostgresql.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/databases")
@CrossOrigin(origins = "*")  // Be more specific in production
public class DatabaseController {

    private final DatabaseService databaseService;

    @Autowired
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // Get all databases
    @GetMapping
    public ResponseEntity<List<Database>> getAllDatabases() {
        List<Database> databases = databaseService.getAllDatabases();
        return new ResponseEntity<>(databases, HttpStatus.OK);
    }

    // Get database by ID
    @GetMapping("/{id}")
    public ResponseEntity<Database> getDatabaseById(@PathVariable Long id) {
        Database database = databaseService.getDatabaseById(id);
        return new ResponseEntity<>(database, HttpStatus.OK);
    }

    // Create a new database
    @PostMapping
    public ResponseEntity<Database> createDatabase(@RequestBody Database database) {
        Database newDatabase = databaseService.createDatabase(database);
        return new ResponseEntity<>(newDatabase, HttpStatus.CREATED);
    }

    // Update database
    @PutMapping("/{id}")
    public ResponseEntity<Database> updateDatabase(@PathVariable Long id, @RequestBody Database database) {
        Database updatedDatabase = databaseService.updateDatabase(id, database);
        return new ResponseEntity<>(updatedDatabase, HttpStatus.OK);
    }

    // Delete database
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDatabase(@PathVariable Long id) {
        databaseService.deleteDatabase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get databases by responsible unit
    @GetMapping("/unit/{unit}")
    public ResponseEntity<List<Database>> getDatabasesByUnit(@PathVariable String unit) {
        List<Database> databases = databaseService.getDatabasesByResponsibleUnit(unit);
        return new ResponseEntity<>(databases, HttpStatus.OK);
    }

    // Get databases by responsible team
    @GetMapping("/team/{team}")
    public ResponseEntity<List<Database>> getDatabasesByTeam(@PathVariable String team) {
        List<Database> databases = databaseService.getDatabasesByResponsibleTeam(team);
        return new ResponseEntity<>(databases, HttpStatus.OK);
    }

    // Search databases by English name
    @GetMapping("/search")
    public ResponseEntity<List<Database>> searchDatabases(@RequestParam String keyword) {
        List<Database> databases = databaseService.searchDatabasesByEnglishName(keyword);
        return new ResponseEntity<>(databases, HttpStatus.OK);
    }
}