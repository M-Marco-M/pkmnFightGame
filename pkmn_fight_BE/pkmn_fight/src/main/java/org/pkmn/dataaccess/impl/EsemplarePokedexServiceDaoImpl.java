package org.pkmn.dataaccess.impl;


import org.pkmn.dataaccess.EsemplarePokedexServiceDao;
import org.pkmn.entity.EsemplarePokedex;
import org.pkmn.repository.PokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EsemplarePokedexServiceDaoImpl implements EsemplarePokedexServiceDao {

    @Autowired
    PokedexRepository pokedexRepository;

    @Override
    public EsemplarePokedex getById(int pokedexNumber) {
        return pokedexRepository.findByPokedexNumber(pokedexNumber);
    }
}
