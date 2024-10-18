package org.pkmn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
}
