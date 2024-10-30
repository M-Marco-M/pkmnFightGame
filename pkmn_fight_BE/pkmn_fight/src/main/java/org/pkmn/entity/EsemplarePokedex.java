package org.pkmn.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
