package org.example.idfpostgresql.controller;

import org.example.idfpostgresql.model.Field;
import org.example.idfpostgresql.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
@CrossOrigin(origins = "*")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    // Get all fields
    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    // Get field by ID
    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Integer id) {
        Field field = fieldService.getFieldById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    // Create a new field
    @PostMapping
    public ResponseEntity<Field> createField(@RequestBody Field field) {
        Field newField = fieldService.createField(field);
        return new ResponseEntity<>(newField, HttpStatus.CREATED);
    }

    // Update field
    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable Integer id, @RequestBody Field field) {
        Field updatedField = fieldService.updateField(id, field);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }

    // Delete field
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Integer id) {
        fieldService.deleteField(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get fields by entity ID
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<Field>> getFieldsByEntityId(@PathVariable Long entityId) {
        List<Field> fields = fieldService.getFieldsByEntityId(entityId);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    // Get fields by type
    @GetMapping("/type/{typeOf}")
    public ResponseEntity<List<Field>> getFieldsByType(@PathVariable String typeOf) {
        List<Field> fields = fieldService.getFieldsByType(typeOf);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    // Search fields by English name
    @GetMapping("/search")
    public ResponseEntity<List<Field>> searchFieldsByEnglishName(@RequestParam String keyword) {
        List<Field> fields = fieldService.searchFieldsByEnglishName(keyword);
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }
}