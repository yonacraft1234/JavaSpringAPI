package org.example.idfpostgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "database", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"database", "hibernateLazyInitializer", "handler"})
    private List<Entity> entities;
}