package org.pkmn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    @Column(name = "type_id")
    private int id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "type_has_pokedex", joinColumns = @JoinColumn (name = "type_id"), inverseJoinColumns = @JoinColumn(name = "pokedex_number"))
    @JsonIgnore
    private List<EsemplarePokedex> speciePokemon;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    @JsonIgnore
    private List<Move> moves;
}
