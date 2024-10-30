package org.pkmn.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pokedex")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EsemplarePokedex {
    @Id
    @Column(name = "pokedex_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pokedexNumber;
    private String name;
    private int att;
    private int def;
    private int hp;


    @ManyToMany(targetEntity = Type.class, mappedBy = "speciePokemon", fetch = FetchType.LAZY)
    private List<Type> tipiPokemon;

    @ManyToMany(targetEntity = Move.class, mappedBy = "speciePokemon", fetch = FetchType.LAZY)
    private List<Type> movepool;
}
