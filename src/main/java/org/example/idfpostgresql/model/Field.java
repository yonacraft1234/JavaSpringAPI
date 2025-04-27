package org.example.idfpostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Table(name = "fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    @Id
    private Integer id;

    @Column(name = "name_hebrew")
    private String nameHebrew;

    @Column(name = "name_english")
    private String nameEnglish;

    @Column(name = "typeof")
    private String typeOf;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "scrapping_date")
    private String scrappingDate; // תאריך גריטה

    @Column(name = "updated_date")
    private String updatedDate; // תאריך עדכון

    @Column(name = "active")
    private Integer active; // 0 - not active, 1 - active
}