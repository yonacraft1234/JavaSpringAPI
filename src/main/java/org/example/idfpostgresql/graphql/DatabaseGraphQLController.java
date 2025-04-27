package org.example.idfpostgresql.graphql;

import org.example.idfpostgresql.model.Database;
import org.example.idfpostgresql.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DatabaseGraphQLController {

    private final DatabaseService databaseService;

    @Autowired
    public DatabaseGraphQLController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @QueryMapping
    public List<Database> databases() {
        return databaseService.getAllDatabases();
    }

    @QueryMapping
    public Database database(@Argument Long id) {
        return databaseService.getDatabaseById(id);
    }

    @QueryMapping
    public List<Database> databasesByUnit(@Argument String unit) {
        return databaseService.getDatabasesByResponsibleUnit(unit);
    }

    @QueryMapping
    public List<Database> databasesByTeam(@Argument String team) {
        return databaseService.getDatabasesByResponsibleTeam(team);
    }

    @QueryMapping
    public List<Database> searchDatabases(@Argument String keyword) {
        return databaseService.searchDatabasesByEnglishName(keyword);
    }

    @MutationMapping
    public Database createDatabase(
            @Argument String dbNameHebrew,
            @Argument String englishName,
            @Argument String responsibleUnit,
            @Argument String responsibleTeam) {

        Database database = new Database();
        database.setDbNameHebrew(dbNameHebrew);
        database.setEnglishName(englishName);
        database.setResponsibleUnit(responsibleUnit);
        database.setResponsibleTeam(responsibleTeam);

        return databaseService.createDatabase(database);
    }

    @MutationMapping
    public Database updateDatabase(
            @Argument Long id,
            @Argument String dbNameHebrew,
            @Argument String englishName,
            @Argument String responsibleUnit,
            @Argument String responsibleTeam) {

        Database database = new Database();
        database.setDbNameHebrew(dbNameHebrew);
        database.setEnglishName(englishName);
        database.setResponsibleUnit(responsibleUnit);
        database.setResponsibleTeam(responsibleTeam);

        return databaseService.updateDatabase(id, database);
    }

    @MutationMapping
    public boolean deleteDatabase(@Argument Long id) {
        try {
            databaseService.deleteDatabase(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}