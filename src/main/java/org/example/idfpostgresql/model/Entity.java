package org.example.idfpostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@jakarta.persistence.Entity
@Table(name = "entities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    @Id
    private Integer id;

    @Column(name = "name_hebrew")
    private String nameHebrew;

    @Column(name = "name_english")
    private String nameEnglish;

    @Column(name = "db_id")
    private Long dbId;

    @Column(name = "scrapping_date")
    private String scrappingDate; // תאריך גריטה

    @Column(name = "updated_date")
    private String updatedDate; // תאריך עדכון

    @Column(name = "active")
    private Integer active; // 0 - not active, 1 - active

}