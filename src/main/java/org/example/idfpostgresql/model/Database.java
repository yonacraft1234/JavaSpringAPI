package org.example.idfpostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@jakarta.persistence.Entity
@Table(name = "databases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "db_name_hebrew")
    private String dbNameHebrew; // שם מאגר מידע

    @Column(name = "english_name")
    private String englishName; // english name

    @Column(name = "responsible_unit")
    private String responsibleUnit; // יחידה אחראית

    @Column(name = "responsible_team")
    private String responsibleTeam; // צוות אחראי

    @Column(name = "classification")
    private String classification; //סייוג

    @Column(name = "scrapping_date")
    private String scrappingDate; // תאריך גריטה

    @Column(name = "updated_date")
    private String updatedDate; // תאריך עדכון

    @Column(name = "active")
    private Integer active; // 0 - not active, 1 - active
}