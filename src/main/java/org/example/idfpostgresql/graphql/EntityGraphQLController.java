package org.example.idfpostgresql.graphql;

import org.example.idfpostgresql.model.Entity;
import org.example.idfpostgresql.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EntityGraphQLController {

    private final EntityService entityService;

    @Autowired
    public EntityGraphQLController(EntityService entityService) {
        this.entityService = entityService;
    }

    @QueryMapping
    public List<Entity> entities() {
        return entityService.getAllEntities();
    }

    @QueryMapping
    public Entity entity(@Argument Integer id) {
        return entityService.getEntityById(id);
    }

    @QueryMapping
    public List<Entity> entitiesByDatabaseId(@Argument Long dbId) {
        return entityService.getEntitiesByDatabaseId(dbId);
    }

    @QueryMapping
    public List<Entity> searchEntitiesByEnglishName(@Argument String keyword) {
        return entityService.searchEntitiesByEnglishName(keyword);
    }

    @QueryMapping
    public List<Entity> searchEntitiesByHebrewName(@Argument String keyword) {
        return entityService.searchEntitiesByHebrewName(keyword);
    }

    @MutationMapping
    public Entity createEntity(
            @Argument Integer id,
            @Argument String nameHebrew,
            @Argument String nameEnglish,
            @Argument Long dbId) {

        Entity entity = new Entity();
        entity.setId(id);
        entity.setNameHebrew(nameHebrew);
        entity.setNameEnglish(nameEnglish);
        entity.setDbId(dbId);

        return entityService.createEntity(entity);
    }

    @MutationMapping
    public Entity updateEntity(
            @Argument Integer id,
            @Argument String nameHebrew,
            @Argument String nameEnglish,
            @Argument Long dbId) {

        Entity entity = new Entity();
        entity.setNameHebrew(nameHebrew);
        entity.setNameEnglish(nameEnglish);
        entity.setDbId(dbId);

        return entityService.updateEntity(id, entity);
    }

    @MutationMapping
    public boolean deleteEntity(@Argument Integer id) {
        try {
            entityService.deleteEntity(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}