package org.pkmn.service;

import org.pkmn.dataaccess.impl.EsemplarePokedexServiceDaoImpl;
import org.pkmn.entity.EsemplarePokedex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsemplarePokedexService {

    @Autowired
    private EsemplarePokedexServiceDaoImpl esemplarePokedexServiceDao;

    public EsemplarePokedex getById(int pokedex_number){
        return esemplarePokedexServiceDao.getById(pokedex_number);
    };
    public EsemplarePokedex getByName(String name) {return esemplarePokedexServiceDao.getByName(name);};
}
