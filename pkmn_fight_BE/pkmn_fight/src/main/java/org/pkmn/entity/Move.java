package org.pkmn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToMany
    @JoinTable(name = "movepool", joinColumns = @JoinColumn (name = "move_id"), inverseJoinColumns = @JoinColumn(name = "pokedex_number"))
    @JsonIgnore
    private List<EsemplarePokedex> speciePokemon;
}
