package org.pkmn.repository;

import org.pkmn.entity.EsemplarePokedex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokedexRepository extends JpaRepository<EsemplarePokedex, Integer> {
    EsemplarePokedex findByPokedexNumber(int pokedexNumber);
}
