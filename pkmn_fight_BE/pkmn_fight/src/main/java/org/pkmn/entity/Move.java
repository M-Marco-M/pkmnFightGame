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
    private int id;
    private String name;
    private int power;
    private int accuracy;
    private int pp;
    @Column(name = "target")
    private String targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;
}
