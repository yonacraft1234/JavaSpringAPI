package org.example.idfpostgresql.service;

import org.example.idfpostgresql.exception.ResourceNotFoundException;
import org.example.idfpostgresql.model.Field;
import org.example.idfpostgresql.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    // Get all fields
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    // Get field by ID
    public Field getFieldById(Integer id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with id: " + id));
    }

    // Create a new field
    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    // Update field
    public Field updateField(Integer id, Field fieldDetails) {
        Field field = getFieldById(id);

        field.setNameHebrew(fieldDetails.getNameHebrew());
        field.setNameEnglish(fieldDetails.getNameEnglish());
        field.setTypeOf(fieldDetails.getTypeOf());
        field.setEntityId(fieldDetails.getEntityId());

        return fieldRepository.save(field);
    }

    // Delete field
    public void deleteField(Integer id) {
        Field field = getFieldById(id);
        fieldRepository.delete(field);
    }

    // Find fields by entity ID
    public List<Field> getFieldsByEntityId(Long entityId) {
        return fieldRepository.findByEntityId(entityId);
    }

    // Find fields by type
    public List<Field> getFieldsByType(String typeOf) {
        return fieldRepository.findByTypeOf(typeOf);
    }

    // Search fields by English name
    public List<Field> searchFieldsByEnglishName(String keyword) {
        return fieldRepository.findByNameEnglishContainingIgnoreCase(keyword);
    }
}