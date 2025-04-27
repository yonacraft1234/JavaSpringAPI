package org.example.idfpostgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"fields", "database", "hibernateLazyInitializer", "handler"})
    private Entity entity;
}