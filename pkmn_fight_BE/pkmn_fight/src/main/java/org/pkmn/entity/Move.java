package org.pkmn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="move")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Move {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "move_id")
    private int move_id;
    private String name;
    private int damage;
}
