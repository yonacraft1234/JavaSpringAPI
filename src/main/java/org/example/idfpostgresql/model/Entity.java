package org.example.idfpostgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "db_id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Database database;

    @OneToMany(mappedBy = "entity", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"entity", "hibernateLazyInitializer", "handler"})
    private List<Field> fields;
}