package org.example.idfpostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@jakarta.persistence.Entity
@Table(name = "scraps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "db_id")
    private Integer dbId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "scrap_date")
    private String scrapDate;

}