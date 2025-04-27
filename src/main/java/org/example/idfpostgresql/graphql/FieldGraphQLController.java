package org.example.idfpostgresql.graphql;

import org.example.idfpostgresql.model.Field;
import org.example.idfpostgresql.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class FieldGraphQLController {

    private final FieldService fieldService;

    @Autowired
    public FieldGraphQLController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @QueryMapping
    public List<Field> fields() {
        return fieldService.getAllFields();
    }

    @QueryMapping
    public Field field(@Argument Integer id) {
        return fieldService.getFieldById(id);
    }

    @QueryMapping
    public List<Field> fieldsByEntityId(@Argument Long entityId) {
        return fieldService.getFieldsByEntityId(entityId);
    }

    @QueryMapping
    public List<Field> fieldsByType(@Argument String typeOf) {
        return fieldService.getFieldsByType(typeOf);
    }

    @QueryMapping
    public List<Field> searchFieldsByEnglishName(@Argument String keyword) {
        return fieldService.searchFieldsByEnglishName(keyword);
    }

    @MutationMapping
    public Field createField(
            @Argument Integer id,
            @Argument String nameHebrew,
            @Argument String nameEnglish,
            @Argument String typeOf,
            @Argument Long entityId) {

        Field field = new Field();
        field.setId(id);
        field.setNameHebrew(nameHebrew);
        field.setNameEnglish(nameEnglish);
        field.setTypeOf(typeOf);
        field.setEntityId(entityId);

        return fieldService.createField(field);
    }

    @MutationMapping
    public Field updateField(
            @Argument Integer id,
            @Argument String nameHebrew,
            @Argument String nameEnglish,
            @Argument String typeOf,
            @Argument Long entityId) {

        Field field = new Field();
        field.setNameHebrew(nameHebrew);
        field.setNameEnglish(nameEnglish);
        field.setTypeOf(typeOf);
        field.setEntityId(entityId);

        return fieldService.updateField(id, field);
    }

    @MutationMapping
    public boolean deleteField(@Argument Integer id) {
        try {
            fieldService.deleteField(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}