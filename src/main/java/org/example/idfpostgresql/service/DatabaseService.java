package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.Database;
import org.example.idfpostgresql.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    private final DatabaseRepository databaseRepository;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    // Get all databases
    public List<Database> getAllDatabases() {
        return databaseRepository.findAll();
    }

    // Get database by ID
    public Database getDatabaseById(Long id) {
        return databaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Database not found with id: " + id));
    }

    // Create a new database
    public Database createDatabase(Database database) {
        return databaseRepository.save(database);
    }

    // Update database
    public Database updateDatabase(Long id, Database databaseDetails) {
        Database database = getDatabaseById(id);

        database.setDbNameHebrew(databaseDetails.getDbNameHebrew());
        database.setEnglishName(databaseDetails.getEnglishName());
        database.setResponsibleUnit(databaseDetails.getResponsibleUnit());
        database.setResponsibleTeam(databaseDetails.getResponsibleTeam());

        return databaseRepository.save(database);
    }

    // Delete database
    public void deleteDatabase(Long id) {
        Database database = getDatabaseById(id);
        databaseRepository.delete(database);
    }

    // Find databases by responsible unit
    public List<Database> getDatabasesByResponsibleUnit(String unit) {
        return databaseRepository.findByResponsibleUnit(unit);
    }

    // Find databases by responsible team
    public List<Database> getDatabasesByResponsibleTeam(String team) {
        return databaseRepository.findByResponsibleTeam(team);
    }

    // Search databases by English name
    public List<Database> searchDatabasesByEnglishName(String keyword) {
        return databaseRepository.findByEnglishNameContainingIgnoreCase(keyword);
    }
}