package org.pkmn.dataaccess;

import org.pkmn.entity.EsemplarePokedex;

public interface EsemplarePokedexServiceDao {
    EsemplarePokedex getById(int pokedexNumber);
    EsemplarePokedex getByName(String name);
}
