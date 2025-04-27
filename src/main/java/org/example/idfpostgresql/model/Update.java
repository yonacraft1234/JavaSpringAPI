package org.example.idfpostgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "updates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "db_id")
    private Integer dbId;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "field_id")
    private Integer fieldId;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}